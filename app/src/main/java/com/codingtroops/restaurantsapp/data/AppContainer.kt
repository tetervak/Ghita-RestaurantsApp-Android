package com.codingtroops.restaurantsapp.data

import com.codingtroops.restaurantsapp.data.remote.RestaurantApi
import com.codingtroops.restaurantsapp.data.repository.RestaurantRepository
import com.codingtroops.restaurantsapp.data.repository.RestaurantRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {

    val restaurantRepository: RestaurantRepository
}

class DefaultAppContainer: AppContainer {

    private val baseUrl = "https://restaurantsapp-android-default-rtdb.firebaseio.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val restaurantApi: RestaurantApi by lazy {
        retrofit.create(RestaurantApi::class.java)
    }

    override val restaurantRepository: RestaurantRepository by lazy{
        RestaurantRepositoryImpl(restaurantApi)
    }
}