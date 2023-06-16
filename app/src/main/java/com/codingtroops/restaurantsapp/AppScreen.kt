package com.codingtroops.restaurantsapp

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.codingtroops.restaurantsapp.ui.details.DetailsScreen
import com.codingtroops.restaurantsapp.ui.details.DetailsViewModel
import com.codingtroops.restaurantsapp.ui.list.ListScreen
import com.codingtroops.restaurantsapp.ui.list.ListViewModel

@Composable
fun AppScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "restaurants") {
        composable(route = "restaurants") {
            val viewModel: ListViewModel = hiltViewModel()
            ListScreen(
                viewModel = viewModel,
                onItemClick = { id -> navController.navigate("restaurants/$id") },
            )
        }
        composable(
            route = "restaurants/{restaurant_id}",
            arguments = listOf(navArgument("restaurant_id") { type = NavType.IntType }),
            deepLinks = listOf(navDeepLink { uriPattern =
                "www.restaurantsapp.details.com/{restaurant_id}" }),
        ) {
            val viewModel: DetailsViewModel = hiltViewModel()
            DetailsScreen(viewModel = viewModel)
        }
    }
}