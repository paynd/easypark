package name.paynd.study.easypark.api

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

data class CitiesResponse(
    @field:Json(name = "status") val status: String,
    @field:Json(name = "message") val message: String?,
    @field:Json(name = "cities") val cities: List<City>
)

data class City(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "lat") val lat: Double,
    @field:Json(name = "lon") val lon: Double,
    @field:Json(name = "r") val radius: Int,
    @field:Json(name = "points") val points: String // todo, add transformation
)

data class CityBoundaries(
    val id: String,
    val boundaries: ArrayList<MapPoint>
)

data class MapPoint(
    val lat: Double,
    val lon: Double
)

data class CityDistance(
    val city: City,
    val distance: Int // lets say it's rounded distance
)