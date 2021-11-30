package name.paynd.study.easypark.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class CitiesPointsAdapter {
    @ToJson
    fun toJson(list: List<MapPoint?>): String {
        throw UnsupportedOperationException()
    }

    @FromJson
    fun fromJson(points: String): List<MapPoint> {
        return points
            .split(",")
            .mapNotNull { point ->
                val chunks = point.split(" ")
                try {
                    val lon = chunks[0].toDouble()
                    val lat = chunks[1].toDouble()
                    MapPoint(lat, lon)
                } catch (e: NumberFormatException) {
                    null
                }
            }
            .toList()
    }
}