package ca.tetervak.restaurantapp.domain

import ca.tetervak.restaurantapp.data.repository.RestaurantRepository

class ClearUseCase(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke() = repository.clearDatabase()
}
