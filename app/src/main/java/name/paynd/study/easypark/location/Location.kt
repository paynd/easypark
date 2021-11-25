package name.paynd.study.easypark.location

import kotlinx.coroutines.flow.SharedFlow
import name.paynd.study.easypark.api.MapPoint

interface Location {
    fun getLocation(): SharedFlow<MapPoint>
}