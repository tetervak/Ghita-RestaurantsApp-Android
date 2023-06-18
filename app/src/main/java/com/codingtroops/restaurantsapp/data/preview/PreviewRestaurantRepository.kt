package com.codingtroops.restaurantsapp.data.preview

import com.codingtroops.restaurantsapp.data.repository.RestaurantRepository
import com.codingtroops.restaurantsapp.domain.Restaurant
import kotlinx.coroutines.coroutineScope

class PreviewRestaurantRepository: RestaurantRepository {
    override suspend fun getAllRestaurants(): List<Restaurant> =
        coroutineScope {
           PreviewDataSource.getAllRestaurants()
        }
}