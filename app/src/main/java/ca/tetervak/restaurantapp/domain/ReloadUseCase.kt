package ca.tetervak.restaurantapp.domain

import ca.tetervak.restaurantapp.data.repository.RestaurantRepository

class ReloadUseCase(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke() = repository.refreshRestaurants()
}
