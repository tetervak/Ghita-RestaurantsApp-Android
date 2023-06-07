package com.codingtroops.restaurantsapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {

    val restaurantRepository: RestaurantRepository
}

class DefaultAppContainer: AppContainer {

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://restaurantsapp-android-default-rtdb.firebaseio.com/")
        .build()

    private val retrofitService: RestaurantsApiService by lazy {
        retrofit.create(RestaurantsApiService::class.java)
    }

    override val restaurantRepository: RestaurantRepository by lazy{
        RestaurantRepositoryNet(retrofitService)
    }
}