package name.paynd.study.easypark.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import name.paynd.study.easypark.MainActivity
import name.paynd.study.easypark.api.CitiesApiService
import name.paynd.study.easypark.api.CitiesRepo
import name.paynd.study.easypark.api.CitiesRepoImpl
import name.paynd.study.easypark.api.createCitiesApiService
import name.paynd.study.easypark.location.LocationProvider
import name.paynd.study.easypark.location.LocationProviderImpl
import name.paynd.study.easypark.ui.cities.CitiesFragment
import name.paynd.study.easypark.ui.map.MapFragment

@[AppScope Component(
    modules = [
        AppModule::class,
        NetworkApiModule::class,
        LocationModule::class,
        VMBindsModule::class]
)]
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(citiesFragment: CitiesFragment)
    fun inject(mapFragment: MapFragment)
}

@Module
class AppModule {
    @Provides
    @AppScope
    fun provideContext(appContext: Application): Context = appContext

    @Provides
    @AppScope
    fun provideAppCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob())
}

@Module
class NetworkApiModule {
    @Provides
    @AppScope
    fun provideCitiesService(): CitiesApiService = createCitiesApiService()

    @Provides
    @AppScope
    fun provideCitiesRepo(
        citiesApiService: CitiesApiService,
        coroutineScope: CoroutineScope,
        locationService: LocationProvider
    ): CitiesRepo =
        CitiesRepoImpl(citiesApiService, locationService, coroutineScope)
}

@Module
class LocationModule {
    @Provides
    @AppScope
    fun provideLocation(context: Context, coroutineScope: CoroutineScope): LocationProvider =
        LocationProviderImpl(context, coroutineScope)
}