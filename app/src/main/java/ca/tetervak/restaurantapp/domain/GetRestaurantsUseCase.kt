package ca.tetervak.restaurantapp.domain

import ca.tetervak.restaurantapp.data.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRestaurantsUseCase(
    private val repository: RestaurantRepository
) {
    operator fun invoke(): Flow<List<Restaurant>> = repository.getAllRestaurantFlow().map { list ->
        list.sortedBy { it.title }
    }
}
