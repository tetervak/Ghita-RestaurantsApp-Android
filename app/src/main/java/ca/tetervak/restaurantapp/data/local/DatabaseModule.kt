package ca.tetervak.restaurantapp.data.local

import android.content.Context
import androidx.room.Room

object DatabaseModule {

    fun provideRestaurantDatabase(
        applicationContext: Context
    ): RestaurantDatabase = Room.databaseBuilder(
        applicationContext, RestaurantDatabase::class.java, "restaurants_database"
    ).fallbackToDestructiveMigration().build()

    fun provideRestaurantDao(
        database: RestaurantDatabase
    ): RestaurantDao = database.restaurantDao

}
