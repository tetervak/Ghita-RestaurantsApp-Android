package com.codingtroops.restaurantsapp.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codingtroops.restaurantsapp.R
import com.codingtroops.restaurantsapp.data.fake.FakeDataSource
import com.codingtroops.restaurantsapp.domain.Restaurant
import com.codingtroops.restaurantsapp.ui.theme.AppTheme

@Composable
fun ListBody(restaurants: List<Restaurant>, onFavoriteClick: (id: Int) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp, horizontal = 8.dp
        )
    ) {
        items(restaurants) { restaurant ->
            RestaurantListItem(
                restaurant = restaurant, onFavoriteClick = onFavoriteClick
            )
        }
    }
}

@Preview
@Composable
fun ListBodyPreview(){
    AppTheme {
        ListBody(
            restaurants = FakeDataSource.getRestaurants(),
            onFavoriteClick = {}
        )
    }
}

@Composable
fun RestaurantListItem(restaurant: Restaurant, onFavoriteClick: (id: Int) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)
        ) {
            RestaurantIcon(Icons.Filled.Place, Modifier.weight(0.15f))
            RestaurantDetails(restaurant.title, restaurant.description, Modifier.weight(0.7f))
            SelectionIcon(
                icon = if (restaurant.isFavorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Filled.FavoriteBorder
                }, onClick = { onFavoriteClick(restaurant.id) }, modifier = Modifier.weight(0.15f)
            )
        }
    }
}

@Composable
private fun SelectionIcon(
    icon: ImageVector, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Image(imageVector = icon,
        contentDescription = stringResource(R.string.selection_icon),
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick() })
}

@Composable
private fun RestaurantIcon(icon: ImageVector, modifier: Modifier) {
    Image(
        imageVector = icon,
        contentDescription = stringResource(R.string.restaurant_icon),
        modifier = modifier.padding(8.dp)
    )
}

@Composable
private fun RestaurantDetails(title: String, description: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = title, style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = description, style = MaterialTheme.typography.bodyLarge
        )
    }
}