package name.paynd.study.easypark.api

import kotlinx.coroutines.flow.SharedFlow
import name.paynd.study.easypark.location.Location
import javax.inject.Inject

class CitiesRepoImpl @Inject constructor(
    val apiService: CitiesApiService,
    val location: Location
) : CitiesRepo {
    override fun getCityBoundaries(cityId: String): CityBoundaries {
        TODO("Not yet implemented")
    }

    override fun getCityDistancesList(): SharedFlow<List<CityDistance>> {
        TODO("Not yet implemented")
    }
}