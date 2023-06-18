package com.codingtroops.restaurantsapp.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codingtroops.restaurantsapp.R
import com.codingtroops.restaurantsapp.data.preview.PreviewDataSource
import com.codingtroops.restaurantsapp.data.preview.PreviewRestaurantRepository
import com.codingtroops.restaurantsapp.domain.Restaurant
import com.codingtroops.restaurantsapp.ui.common.RestaurantDetails
import com.codingtroops.restaurantsapp.ui.common.RestaurantIcon
import com.codingtroops.restaurantsapp.ui.list.ListBody
import com.codingtroops.restaurantsapp.ui.theme.AppTheme

@Composable
fun DetailsBody(restaurant: Restaurant) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        RestaurantIcon(
            icon = Icons.Filled.Place,
            modifier = Modifier.padding(top = 32.dp, bottom = 32.dp)
        )
        RestaurantDetails(
            title = restaurant.title,
            description = restaurant.description,
            modifier = Modifier.padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        Text(stringResource(R.string.more_info_coming_soon))
    }
}

@Preview
@Composable
fun DetailsBodyPreview(){
    AppTheme {
        DetailsBody(
            restaurant = PreviewDataSource.getRestaurantById(1)
        )
    }
}
