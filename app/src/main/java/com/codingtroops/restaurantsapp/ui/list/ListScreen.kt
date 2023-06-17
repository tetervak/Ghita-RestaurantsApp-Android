package com.codingtroops.restaurantsapp.ui.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.codingtroops.restaurantsapp.ui.common.ErrorBody
import com.codingtroops.restaurantsapp.ui.common.LoadingBody

@Composable
fun ListScreen(viewModel: ListViewModel) {
    val state: State<ListUiState> = viewModel.listUiState
    when (val listUiState: ListUiState = state.value) {

        is ListUiState.Loading -> LoadingBody()

        is ListUiState.Loaded -> ListBody(restaurants = listUiState.restaurants,
            onFavoriteClick = { id: Int -> viewModel.toggleFavorite(id) })

        is ListUiState.Error -> ErrorBody(onRetry = { viewModel.retryLoadRestaurants() })
    }
}

