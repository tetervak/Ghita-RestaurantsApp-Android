package com.codingtroops.restaurantsapp.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.codingtroops.restaurantsapp.ui.common.ErrorBody
import com.codingtroops.restaurantsapp.ui.common.LoadingBody

@Composable
fun DetailsScreen(viewModel: DetailsViewModel) {
    val state: State<DetailsUiState> = viewModel.detailsUiState
    when (val detailsUiState: DetailsUiState = state.value) {

        is DetailsUiState.Loading -> LoadingBody()

        is DetailsUiState.Loaded -> DetailsBody(restaurant = detailsUiState.restaurant)

        is DetailsUiState.Error -> ErrorBody(onRetry = { viewModel.retryLoadRestaurant() })
    }
}