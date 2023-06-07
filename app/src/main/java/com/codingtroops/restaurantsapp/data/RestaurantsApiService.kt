package com.codingtroops.restaurantsapp.data

import com.codingtroops.restaurantsapp.model.Restaurant
import retrofit2.http.GET


interface RestaurantsApiService {
    @GET("restaurants.json")
    suspend fun getRestaurants(): List<Restaurant>
}