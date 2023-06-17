package com.codingtroops.restaurantsapp.data.preview

import com.codingtroops.restaurantsapp.data.repository.RestaurantRepository
import com.codingtroops.restaurantsapp.domain.Restaurant
import kotlinx.coroutines.coroutineScope

class PreviewRestaurantRepository: RestaurantRepository {
    override suspend fun getRestaurants(): List<Restaurant> =
        coroutineScope {
           PreviewDataSource.getRestaurants()
        }
}