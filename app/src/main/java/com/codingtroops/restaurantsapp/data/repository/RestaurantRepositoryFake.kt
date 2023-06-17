package com.codingtroops.restaurantsapp.data.repository

import com.codingtroops.restaurantsapp.data.fake.FakeDataSource
import com.codingtroops.restaurantsapp.domain.Restaurant
import kotlinx.coroutines.coroutineScope

class RestaurantRepositoryFake: RestaurantRepository {
    override suspend fun getRestaurants(): List<Restaurant> =
        coroutineScope {
           FakeDataSource.getRestaurants()
        }
}