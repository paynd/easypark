package name.paynd.study.easypark.location

import kotlinx.coroutines.flow.SharedFlow
import name.paynd.study.easypark.api.MapPoint

class LocationImpl : Location {
    override fun getLocation(): SharedFlow<MapPoint> {
        TODO("Not yet implemented")
    }
}