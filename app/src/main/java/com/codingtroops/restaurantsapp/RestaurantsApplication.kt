package com.codingtroops.restaurantsapp

import android.app.Application
import com.codingtroops.restaurantsapp.data.repository.RestaurantRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class RestaurantsApplication : Application() {

    @Inject
    lateinit var repository: RestaurantRepository

    companion object {
        private val mainScope = MainScope()
    }

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }

    override fun onCreate() {
        super.onCreate()
        mainScope.launch(errorHandler) {
            repository.refreshRestaurants()
        }
    }
}