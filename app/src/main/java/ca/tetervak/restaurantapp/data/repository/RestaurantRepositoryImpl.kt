package ca.tetervak.restaurantapp.data.repository

import android.util.Log
import ca.tetervak.restaurantapp.data.local.IsFavorite
import ca.tetervak.restaurantapp.data.local.LocalRestaurant
import ca.tetervak.restaurantapp.data.local.RestaurantDao
import ca.tetervak.restaurantapp.data.remote.RemoteRestaurant
import ca.tetervak.restaurantapp.data.remote.RestaurantApi
import ca.tetervak.restaurantapp.domain.Restaurant
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RestaurantRepositoryImpl(
    private val restaurantApi: RestaurantApi,
    private val restaurantDao: RestaurantDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RestaurantRepository {

    override fun getAllRestaurantFlow(): Flow<List<Restaurant>> =
        restaurantDao.getAllRestaurantFlow().map { list -> list.map { it.toRestaurant() } }
            .flowOn(dispatcher)

    override suspend fun getRestaurantById(id: Int): Restaurant? = withContext(dispatcher) {
        restaurantDao.getRestaurantById(id)?.toRestaurant()
    }

    override suspend fun toggleIsFavoriteById(id: Int) = withContext(dispatcher) {
        restaurantDao.toggleIsFavoriteById(id)
    }

    override suspend fun setIsFavoriteById(id: Int, isFavorite: Boolean) =
        withContext(dispatcher) {
            restaurantDao.setIsFavoriteById(id, isFavorite)
        }

    override suspend fun refreshRestaurants(): Unit = withContext(dispatcher) {
        try {
            val remoteRestaurants = restaurantApi.getAllRestaurants()
            val localRestaurants = remoteRestaurants.map { it.toLocalRestaurant() }
            val idsOfFavorites: List<Int> = restaurantDao.getIdsOfFavoriteRestaurants()
            val isFavoriteList: List<IsFavorite> =
                idsOfFavorites.map { id -> IsFavorite(id = id, isFavorite = true) }

            restaurantDao.refreshRestaurants(localRestaurants)
            restaurantDao.updateIsFavorite(isFavoriteList)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Log.e("RestaurantRepository", "Error refreshing restaurants: ${e.message}", e)
        }
    }

    override suspend fun clearDatabase() = withContext(dispatcher) {
        restaurantDao.deleteAllRestaurants()
    }
}

fun LocalRestaurant.toRestaurant(): Restaurant = Restaurant(
    id = this.id, title = this.title, description = this.description, isFavorite = this.isFavorite
)

fun RemoteRestaurant.toLocalRestaurant(): LocalRestaurant = LocalRestaurant(
    id = this.id, title = this.title, description = this.description, isFavorite = false
)
