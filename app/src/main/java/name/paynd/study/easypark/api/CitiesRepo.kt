package name.paynd.study.easypark.api

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow

interface CitiesRepo {
    val citiesFlow : Flow<List<City>?>
    val citiesDistances : Flow<List<CityDistance>?>

    fun loadCity(name: String) : Flow<City?>
}