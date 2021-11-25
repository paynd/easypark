package name.paynd.study.easypark.api

import kotlinx.coroutines.flow.SharedFlow

interface CitiesRepo {
    fun getCityBoundaries(cityId: String): CityBoundaries
    fun getCityDistancesList(): SharedFlow<List<CityDistance>>
}