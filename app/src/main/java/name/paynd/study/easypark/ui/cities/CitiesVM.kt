package name.paynd.study.easypark.ui.cities

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import name.paynd.study.easypark.api.CitiesRepo
import name.paynd.study.easypark.api.CityDistance
import javax.inject.Inject

class CitiesVM @Inject constructor(
    citiesRepo: CitiesRepo
) : ViewModel() {
    val citiesDistances: Flow<List<CityDistance>?> = citiesRepo.citiesDistances
}