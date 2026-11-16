package ca.tetervak.restaurantapp

import android.app.Application
import ca.tetervak.restaurantapp.data.repository.RestaurantRepository
import ca.tetervak.restaurantapp.di.appModules
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RestaurantApplication : Application() {

    private val repository: RestaurantRepository by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RestaurantApplication)
            modules(appModules)
        }

        MainScope().launch(Dispatchers.IO) {
            repository.refreshRestaurants()
        }
    }
}
