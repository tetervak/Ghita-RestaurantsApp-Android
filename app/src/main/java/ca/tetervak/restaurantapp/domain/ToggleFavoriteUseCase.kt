package ca.tetervak.restaurantapp.domain

import ca.tetervak.restaurantapp.data.repository.RestaurantRepository

class ToggleFavoriteUseCase(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(id: Int, currentIsFavorite: Boolean) =
        repository.setIsFavoriteById(id, currentIsFavorite.not())
}
