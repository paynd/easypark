package name.paynd.study.easypark.ui.map

import androidx.lifecycle.ViewModel
import name.paynd.study.easypark.api.CitiesRepo
import name.paynd.study.easypark.location.LocationProvider
import javax.inject.Inject

class MapVM @Inject constructor(
    private val citiesRepo: CitiesRepo,
    locationProvider: LocationProvider
) : ViewModel() {
    val location = locationProvider.locationState
    fun updateHomeCity(cityName: String) = citiesRepo.loadCity(cityName)
}