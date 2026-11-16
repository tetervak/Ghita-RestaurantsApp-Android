package ca.tetervak.restaurantapp.di

import androidx.room.Room
import ca.tetervak.restaurantapp.data.local.RestaurantDao
import ca.tetervak.restaurantapp.data.local.RestaurantDatabase
import ca.tetervak.restaurantapp.data.remote.RestaurantApi
import ca.tetervak.restaurantapp.data.repository.RestaurantRepository
import ca.tetervak.restaurantapp.data.repository.RestaurantRepositoryImpl
import ca.tetervak.restaurantapp.domain.ClearUseCase
import ca.tetervak.restaurantapp.domain.GetRestaurantsUseCase
import ca.tetervak.restaurantapp.domain.ReloadUseCase
import ca.tetervak.restaurantapp.domain.ToggleFavoriteUseCase
import ca.tetervak.restaurantapp.ui.details.DetailsViewModel
import ca.tetervak.restaurantapp.ui.list.ListViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit

val databaseModule = module {
    single<RestaurantDatabase> {
        Room.databaseBuilder(
            androidContext(), RestaurantDatabase::class.java, "restaurants_database"
        ).fallbackToDestructiveMigration(true).build()
    }
    single<RestaurantDao> {
        get<RestaurantDatabase>().restaurantDao
    }
}

val networkModule = module {
    single<Retrofit> {
        val baseUrl = "https://restaurants-db-default-rtdb.firebaseio.com/"
        val json = Json {
            ignoreUnknownKeys = true
        }
        Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl)
            .build()
    }
    single<RestaurantApi> {
        get<Retrofit>().create(RestaurantApi::class.java)
    }
}

val repositoryModule = module {
    single<RestaurantRepository> { RestaurantRepositoryImpl(get(), get()) }
}

val domainModule = module {
    factoryOf(::ClearUseCase)
    factoryOf(::GetRestaurantsUseCase)
    factoryOf(::ReloadUseCase)
    factoryOf(::ToggleFavoriteUseCase)
}

val viewModelModule = module {
    viewModelOf(::ListViewModel)
    viewModelOf(::DetailsViewModel)
}

val appModules = listOf(
    databaseModule,
    networkModule,
    repositoryModule,
    domainModule,
    viewModelModule,
)
