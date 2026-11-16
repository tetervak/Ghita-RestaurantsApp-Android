package ca.tetervak.restaurantapp.di

import ca.tetervak.restaurantapp.data.local.DatabaseModule
import ca.tetervak.restaurantapp.data.remote.RetrofitModule
import ca.tetervak.restaurantapp.data.repository.RestaurantRepository
import ca.tetervak.restaurantapp.data.repository.RestaurantRepositoryImpl
import ca.tetervak.restaurantapp.domain.ClearUseCase
import ca.tetervak.restaurantapp.domain.GetRestaurantsUseCase
import ca.tetervak.restaurantapp.domain.ReloadUseCase
import ca.tetervak.restaurantapp.domain.ToggleFavoriteUseCase
import ca.tetervak.restaurantapp.ui.details.DetailsViewModel
import ca.tetervak.restaurantapp.ui.list.ListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val databaseModule = module {
    single { DatabaseModule.provideRestaurantDatabase(androidContext()) }
    single { DatabaseModule.provideRestaurantDao(get()) }
}

val networkModule = module {
    single { RetrofitModule.retrofit() }
    single { RetrofitModule.provideRestaurantApi(get()) }
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

val appModule = listOf(
    databaseModule,
    networkModule,
    repositoryModule,
    domainModule,
    viewModelModule,
)
