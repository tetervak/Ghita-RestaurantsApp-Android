package com.codingtroops.restaurantsapp.data.repository

import com.codingtroops.restaurantsapp.data.remote.RemoteRestaurant
import com.codingtroops.restaurantsapp.data.remote.RestaurantApi
import com.codingtroops.restaurantsapp.domain.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class RestaurantRepositoryImpl(
    private val retrofitService: RestaurantApi
): RestaurantRepository {

    override suspend fun getAllRestaurants(): List<Restaurant> =
        withContext(Dispatchers.IO) {

            //fake loading delay, 2 seconds
            delay(2000)

            retrofitService.getRestaurants().map{ it.toRestaurant() }
        }
}

fun RemoteRestaurant.toRestaurant(): Restaurant =
    Restaurant(
        id = this.id,
        title = this.title,
        description = this.description,
        isFavorite = false
    )