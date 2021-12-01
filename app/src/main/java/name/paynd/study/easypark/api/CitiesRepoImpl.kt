package name.paynd.study.easypark.api

import android.location.Location
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

    override val citiesDistances: Flow<RepoResult> =
        locationService.locationState.combine(citiesFlow) { locationState, cities ->
            when (locationState) {
                is LocationState.Success -> {
                    val list = cities.map { city ->
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
                    RepoResult.Success(list)
                }
                is LocationState.Error -> RepoResult.Error("Location error: ${locationState.message}")
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