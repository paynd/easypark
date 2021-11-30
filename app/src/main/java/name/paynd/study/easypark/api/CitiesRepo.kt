package name.paynd.study.easypark.api

import kotlinx.coroutines.flow.Flow

interface CitiesRepo {
    val citiesFlow: Flow<List<City>?>
    val citiesDistances: Flow<List<CityDistance>?>

    fun loadCity(name: String): Flow<City?>
}