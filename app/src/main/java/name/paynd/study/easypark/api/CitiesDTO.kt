package name.paynd.study.easypark.api

import kotlinx.serialization.Serializable

@Serializable
data class CitiesResponse(
    val status: String,
    val message: String?,
    val cities: List<City>
)

@Serializable
data class City(
    val name: String,
    val lat: Double,
    val lon: Double,
    val r: Int,
    val points: String // todo, add transformation
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