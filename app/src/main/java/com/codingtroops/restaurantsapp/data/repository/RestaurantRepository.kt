package com.codingtroops.restaurantsapp.data.repository

import com.codingtroops.restaurantsapp.domain.Restaurant

interface RestaurantRepository {

    suspend fun getRestaurants(): List <Restaurant>
}