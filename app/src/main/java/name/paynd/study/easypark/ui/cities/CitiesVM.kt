package name.paynd.study.easypark.ui.cities

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import name.paynd.study.easypark.api.CitiesRepo
import javax.inject.Inject

class CitiesVM @Inject constructor(
    private val citiesRepo: CitiesRepo
) : ViewModel() {
    fun loadCities() {
        viewModelScope.launch {
            citiesRepo.citiesDistances.collect { list ->
                list?.forEach {
                    Log.d("####", "collect ${it.city.name} - ${it.distance}")
                }
            }
        }
    }
}