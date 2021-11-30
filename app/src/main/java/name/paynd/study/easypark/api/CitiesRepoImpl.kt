package name.paynd.study.easypark.api

import android.location.Location
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import name.paynd.study.easypark.location.LocationProvider
import name.paynd.study.easypark.location.LocationState
import javax.inject.Inject

class CitiesRepoImpl @Inject constructor(
    private val apiService: CitiesApiService,
    locationService: LocationProvider,
    externalScope: CoroutineScope
) : CitiesRepo {
    override val citiesFlow = flow { emit(apiService.getCities().cities) }
        .flowOn(Dispatchers.IO)
        .shareIn(
            externalScope,
            replay = 1,
            started = SharingStarted.WhileSubscribed()
        )

    override val citiesDistances =
        locationService.locationState.combine(citiesFlow) { locationState, cities ->
            when (locationState) {
                is LocationState.Success -> {
                    cities.map { city ->
                        CityDistance(
                            city,
                            Location(city.name)
                                .apply {
                                    latitude = city.lat
                                    longitude = city.lon
                                }
                                .distanceTo(locationState.location)
                                .toKm()
                        )
                    }
                }
                else -> null
            }
        }

    override fun loadCity(name: String): Flow<City?> {
        return citiesFlow.map { list ->
            list.findLast { city ->
                city.name == name
            }
        }
    }

    private fun Float.toKm() = (this / 1000).toInt()
}