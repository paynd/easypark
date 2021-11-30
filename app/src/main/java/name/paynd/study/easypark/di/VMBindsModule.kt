package name.paynd.study.easypark.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import name.paynd.study.easypark.ui.cities.CitiesVM
import name.paynd.study.easypark.ui.map.MapVM

@Module
interface VMBindsModule {
    @Binds
    abstract fun bindViewModelFactory(factory: VMFactory): ViewModelProvider.Factory

    @[AppScope Binds IntoMap VMKey(CitiesVM::class)]
    fun bindClientListViewModel(sourcesViewModel: CitiesVM): ViewModel

    @[AppScope Binds IntoMap VMKey(MapVM::class)]
    fun bindMapViewModel(sourcesViewModel: MapVM): ViewModel
}