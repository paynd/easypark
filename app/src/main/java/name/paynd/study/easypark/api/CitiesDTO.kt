package name.paynd.study.easypark.api

import kotlinx.serialization.Serializable

@Serializable
data class CitiesResponse(
    val status: String,
    val message: String?,
    val cities: Cities
)

@Serializable
data class Cities(
    val name: String,
    val lat: Double,
    val lon: Double,
    val r: Int,
    val points: String // todo, add transformation
)