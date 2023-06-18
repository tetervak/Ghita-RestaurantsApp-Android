package com.codingtroops.restaurantsapp.ui.list

import com.codingtroops.restaurantsapp.domain.Restaurant

sealed interface ListUiState {
    object Loading : ListUiState
    data class Loaded(var restaurants: List<Restaurant>) : ListUiState
    object Error : ListUiState
}