package com.codingtroops.restaurantsapp.data

import com.codingtroops.restaurantsapp.model.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantRepositoryNet(
    private val retrofitService: RestaurantsApiService
): RestaurantRepository {

    override suspend fun getRestaurants(): List<Restaurant> =
        withContext(Dispatchers.IO) {
            retrofitService.getRestaurants()
        }
}