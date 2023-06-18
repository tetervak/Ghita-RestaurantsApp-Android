package com.codingtroops.restaurantsapp.data.repository

import com.codingtroops.restaurantsapp.data.remote.RemoteRestaurant
import com.codingtroops.restaurantsapp.data.remote.RestaurantApi
import com.codingtroops.restaurantsapp.domain.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantApi: RestaurantApi
): RestaurantRepository {

    override suspend fun getAllRestaurants(): List<Restaurant> =
        withContext(Dispatchers.IO) {

            //fake loading delay, 2 seconds
            delay(2000)

            restaurantApi.getAllRestaurants().map{ it.toRestaurant() }
        }

    override suspend fun getRestaurantById(id: Int): Restaurant =
        withContext(Dispatchers.IO) {

            //fake loading delay, 2 seconds
            delay(2000)

            val map =  restaurantApi.getRestaurantById(id)
            map.values.first().toRestaurant()
        }
}

fun RemoteRestaurant.toRestaurant(): Restaurant =
    Restaurant(
        id = this.id,
        title = this.title,
        description = this.description,
        isFavorite = false
    )