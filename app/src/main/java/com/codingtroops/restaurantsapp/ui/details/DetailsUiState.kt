package com.codingtroops.restaurantsapp.ui.details

import com.codingtroops.restaurantsapp.domain.Restaurant

interface DetailsUiState {
    object Loading : DetailsUiState
    data class Loaded(var restaurant: Restaurant) : DetailsUiState
    object Error : DetailsUiState
}