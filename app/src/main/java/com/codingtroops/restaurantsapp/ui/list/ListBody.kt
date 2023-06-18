package com.codingtroops.restaurantsapp.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codingtroops.restaurantsapp.R
import com.codingtroops.restaurantsapp.data.preview.PreviewDataSource
import com.codingtroops.restaurantsapp.domain.Restaurant
import com.codingtroops.restaurantsapp.ui.common.RestaurantDetails
import com.codingtroops.restaurantsapp.ui.common.RestaurantIcon
import com.codingtroops.restaurantsapp.ui.theme.AppTheme

@Composable
fun ListBody(
    restaurants: List<Restaurant>,
    onItemClick: (id: Int) -> Unit,
    onFavoriteClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp, horizontal = 8.dp
        ), modifier = modifier
    ) {
        items(restaurants) { restaurant ->
            RestaurantItem(item = restaurant,
                onFavoriteClick = { onFavoriteClick(restaurant.id) },
                onItemClick = { onItemClick(restaurant.id) })
        }
    }
}

@Preview
@Composable
fun ListBodyPreview(){
    AppTheme {
        ListBody(
            restaurants = PreviewDataSource.getAllRestaurants(),
            onFavoriteClick = {},
            onItemClick = {}
        )
    }
}

@Composable
fun RestaurantItem(
    item: Restaurant, onFavoriteClick: () -> Unit, onItemClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onItemClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)
        ) {
            RestaurantIcon(
                icon = Icons.Filled.Place, modifier = Modifier.weight(0.15f)
            )
            RestaurantDetails(
                title = item.title, description = item.description, modifier = Modifier.weight(0.7f)
            )
            SelectionIcon(
                icon = if (item.isFavorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Filled.FavoriteBorder
                }, onClick = onFavoriteClick, modifier = Modifier.weight(0.15f)
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