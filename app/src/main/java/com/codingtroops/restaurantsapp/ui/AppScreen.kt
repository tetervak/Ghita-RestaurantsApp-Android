package com.codingtroops.restaurantsapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codingtroops.restaurantsapp.data.preview.PreviewRestaurantRepository
import com.codingtroops.restaurantsapp.ui.theme.AppTheme
import com.codingtroops.restaurantsapp.ui.list.ListScreen
import com.codingtroops.restaurantsapp.ui.list.ListViewModel

@Composable
fun AppScreen() {
    // this composable will contain navigation logic later
    val viewModel: ListViewModel = viewModel(factory = AppViewModelProvider.Factory)
    ListScreen(viewModel)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        ListScreen(ListViewModel(PreviewRestaurantRepository()))
    }
}