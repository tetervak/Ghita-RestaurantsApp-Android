package com.codingtroops.restaurantsapp.data.fake

import com.codingtroops.restaurantsapp.domain.Restaurant
import kotlin.random.Random

object FakeDataSource {

    private val random: Random = Random.Default

    fun getRestaurants(): List<Restaurant> = buildList {
        for (i in 1..30) add(
            Restaurant(
                id = i,
                title = "Fake title $i",
                description = "Fake description $i",
                isFavorite = random.nextBoolean()
            )
        )
    }
}