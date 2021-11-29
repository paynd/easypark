package name.paynd.study.easypark.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow

interface CitiesRepo {
//    fun getCityBoundaries(cityId: String): CityBoundaries
//    suspend fun getCitiesList(): Flow<City>
//    suspend fun getCityDistancesList(): Flow<CitiesRepoImpl.CityDistanceResult>

    val citiesFlow : Flow<List<City>?>
    val citiesDistances : Flow<List<CityDistance>?>
}