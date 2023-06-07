package com.codingtroops.restaurantsapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.codingtroops.restaurantsapp.data.RestaurantRepository
import com.codingtroops.restaurantsapp.model.Restaurant
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel(
    repository: RestaurantRepository
) : ViewModel() {

    private val _uiState: MutableState<List<Restaurant>> =
        mutableStateOf(emptyList())
    val uiState: State<List<Restaurant>> = _uiState

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    init {
        viewModelScope.launch(errorHandler) {
            _uiState.value = repository.getRestaurants()
        }
    }

    fun toggleFavorite(id: Int) {
        val restaurants = uiState.value.toMutableList()
        val itemIndex = restaurants.indexOfFirst { it.id == id }
        val item = restaurants[itemIndex]
        restaurants[itemIndex] = item.copy(isFavorite = !item.isFavorite)
        _uiState.value = restaurants.toList()
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RestaurantsApplication)
                val restaurantRepository = application.container.restaurantRepository
                MainViewModel(repository = restaurantRepository)
            }
        }
    }
}