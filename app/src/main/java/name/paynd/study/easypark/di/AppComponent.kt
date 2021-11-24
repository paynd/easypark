package name.paynd.study.easypark.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import name.paynd.study.easypark.api.CitiesApiService
import name.paynd.study.easypark.api.createCitiesApiService

@[AppScope Component(modules = [AppModule::class, NetworkApiModule::class, LocationModule::class])]
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}

@Module
class AppModule() {

//    @Provides
//    @AppScope
//    fun provide
}

@Module
class NetworkApiModule() {

    @Provides
    @AppScope
    fun provideCitiesService(): CitiesApiService = createCitiesApiService()
}

@Module
class LocationModule() {

}