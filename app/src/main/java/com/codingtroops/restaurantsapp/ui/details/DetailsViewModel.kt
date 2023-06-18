package com.codingtroops.restaurantsapp.ui.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtroops.restaurantsapp.data.repository.RestaurantRepository
import com.codingtroops.restaurantsapp.domain.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: RestaurantRepository,
    stateHandle: SavedStateHandle
): ViewModel() {

    private val _detailsUiState: MutableState<DetailsUiState> =
        mutableStateOf(DetailsUiState.Loading)
    val detailsUiState: State<DetailsUiState> = _detailsUiState

    private val id: Int = stateHandle.get<Int>("restaurant_id")!!

    init {
        loadRestaurant()
    }

    private fun loadRestaurant() {
        viewModelScope.launch {
            try{
                val restaurant: Restaurant = repository.getRestaurantById(id)
                _detailsUiState.value = DetailsUiState.Loaded(restaurant = restaurant)
            } catch(e: IOException) {
                _detailsUiState.value = DetailsUiState.Error
                e.printStackTrace()
            }
        }
    }

    fun retryLoadRestaurant(){
        _detailsUiState.value = DetailsUiState.Loading
        loadRestaurant()
    }
}