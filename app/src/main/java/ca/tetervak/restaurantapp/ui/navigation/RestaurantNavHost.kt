package ca.tetervak.restaurantapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ca.tetervak.restaurantapp.ui.details.DetailsScreen
import ca.tetervak.restaurantapp.ui.details.DetailsViewModel
import ca.tetervak.restaurantapp.ui.list.ListScreen
import ca.tetervak.restaurantapp.ui.list.ListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RestaurantNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = ListDestination.route) {
        composable(route = ListDestination.route) {
            val viewModel: ListViewModel = koinViewModel()
            ListScreen(
                viewModel = viewModel,
                onItemClick = { id ->
                    navController.navigate("${DetailsDestination.route}/$id")
                },
            )
        }
        composable(
            route = DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.restaurantIdArg) {
                type = NavType.IntType
            })
        ) {
            val viewModel: DetailsViewModel = koinViewModel()
            DetailsScreen(viewModel = viewModel, onBack = { navController.popBackStack() })
        }
    }
}
