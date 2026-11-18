package ca.tetervak.restaurantapp.ui.details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import ca.tetervak.restaurantapp.data.repository.RestaurantRepository
import ca.tetervak.restaurantapp.domain.Restaurant
import ca.tetervak.restaurantapp.ui.DetailsRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    repository: RestaurantRepository,
    stateHandle: SavedStateHandle,
) : ViewModel() {

    private val _detailsUiState: MutableStateFlow<DetailsUiState> =
        MutableStateFlow(DetailsUiState.Loading)
    val detailsUiState: StateFlow<DetailsUiState> = _detailsUiState

    init {
        val route: DetailsRoute = stateHandle.toRoute<DetailsRoute>()
        val id: Int = route.id
        viewModelScope.launch {
            val restaurant: Restaurant? = repository.getRestaurantById(id)
            if(restaurant != null){
                _detailsUiState.update {
                    DetailsUiState.Success(restaurant = restaurant)
                }
            } else {
                Log.e("DetailsViewModel", "data for id=$id is not found")
            }
        }
    }
}
