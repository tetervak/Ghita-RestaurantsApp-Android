package com.codingtroops.restaurantsapp.data.preview

import com.codingtroops.restaurantsapp.data.repository.RestaurantRepository
import com.codingtroops.restaurantsapp.domain.Restaurant
import kotlinx.coroutines.coroutineScope

class PreviewRestaurantRepository: RestaurantRepository {

    private val restaurants: List<Restaurant> = PreviewDataSource.getAllRestaurants()

    override suspend fun getAllRestaurants(): List<Restaurant> =
        coroutineScope {
            restaurants
        }

    override suspend fun getRestaurantById(id: Int): Restaurant =
        coroutineScope {
            restaurants.find{ it.id == id }!!
        }
}