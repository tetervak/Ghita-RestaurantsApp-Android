package com.codingtroops.restaurantsapp.ui.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.codingtroops.restaurantsapp.ui.common.ErrorBody
import com.codingtroops.restaurantsapp.ui.common.LoadingBody

@Composable
fun ListScreen(viewModel: ListViewModel, onItemClick: (id: Int) -> Unit) {
    val state: State<ListUiState> = viewModel.listUiState
    when (val listUiState: ListUiState = state.value) {

        is ListUiState.Loading -> LoadingBody()

        is ListUiState.Loaded -> ListBody(restaurants = listUiState.restaurants,
            onItemClick = onItemClick,
            onFavoriteClick = { id: Int -> viewModel.toggleFavorite(id) }
        )

        is ListUiState.Error -> ErrorBody(onRetry = { viewModel.retryLoadRestaurants() })
    }
}
