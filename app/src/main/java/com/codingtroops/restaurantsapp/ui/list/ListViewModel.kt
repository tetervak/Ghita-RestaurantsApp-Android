package com.codingtroops.restaurantsapp.ui.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtroops.restaurantsapp.data.repository.RestaurantRepository
import com.codingtroops.restaurantsapp.domain.Restaurant
import kotlinx.coroutines.launch
import java.io.IOException

class ListViewModel(
    private val repository: RestaurantRepository
) : ViewModel() {

    private val _listUiState: MutableState<ListUiState> =
        mutableStateOf(ListUiState.Loading)
    val listUiState: State<ListUiState> = _listUiState

    init {
        loadRestaurants()
    }

    private fun loadRestaurants() {
        viewModelScope.launch {
            try{
                val restaurants: List<Restaurant> = repository.getRestaurants()
                _listUiState.value = ListUiState.Loaded(restaurants = restaurants)
            } catch(e: IOException) {
                _listUiState.value = ListUiState.Error
                e.printStackTrace()
            }
        }
    }

    fun retryLoadRestaurants(){
        _listUiState.value = ListUiState.Loading
        loadRestaurants()
    }

    fun toggleFavorite(id: Int) {
        val uiState: ListUiState = listUiState.value
        if(uiState is ListUiState.Loaded){
            val restaurants = uiState.restaurants.toMutableList()
            val itemIndex = restaurants.indexOfFirst { it.id == id }
            val item = restaurants[itemIndex]
            restaurants[itemIndex] = item.copy(isFavorite = item.isFavorite.not())
            _listUiState.value = ListUiState.Loaded(restaurants = restaurants)
        }
    }
}

