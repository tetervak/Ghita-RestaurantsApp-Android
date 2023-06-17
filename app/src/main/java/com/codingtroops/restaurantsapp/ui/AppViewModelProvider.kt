package com.codingtroops.restaurantsapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.codingtroops.restaurantsapp.RestaurantsApplication
import com.codingtroops.restaurantsapp.ui.list.ListViewModel

object AppViewModelProvider {
    val Factory: ViewModelProvider.Factory = viewModelFactory {

        // Initializer for ListViewModel
        initializer {
            ListViewModel(
                repository = restaurantsApplication().container.restaurantRepository)
        }
    }
}

fun CreationExtras.restaurantsApplication(): RestaurantsApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as RestaurantsApplication)