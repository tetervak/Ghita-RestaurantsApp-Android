package ca.tetervak.restaurantapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.tetervak.restaurantapp.ui.details.DetailsScreen
import ca.tetervak.restaurantapp.ui.details.DetailsViewModel
import ca.tetervak.restaurantapp.ui.list.ListScreen
import ca.tetervak.restaurantapp.ui.list.ListViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object ListRoute

@Serializable
data class DetailsRoute(val id: Int)


@Composable
fun AppRootScreen(navController: NavHostController = rememberNavController()) {

    NavHost(navController, startDestination = ListRoute) {
        composable<ListRoute>{
            val viewModel: ListViewModel = koinViewModel()
            ListScreen(
                viewModel = viewModel,
                onItemClick = { id ->
                    navController.navigate(DetailsRoute(id))
                },
            )
        }
        composable<DetailsRoute>{
            val viewModel: DetailsViewModel = koinViewModel()
            DetailsScreen(viewModel = viewModel, onBack = { navController.popBackStack() })
        }
    }
}