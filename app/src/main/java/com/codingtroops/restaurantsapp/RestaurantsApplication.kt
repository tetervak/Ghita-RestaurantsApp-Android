package com.codingtroops.restaurantsapp

import android.app.Application
import com.codingtroops.restaurantsapp.data.AppContainer
import com.codingtroops.restaurantsapp.data.DefaultAppContainer

class RestaurantsApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}