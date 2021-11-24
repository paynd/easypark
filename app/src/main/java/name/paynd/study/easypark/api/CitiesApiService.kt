package name.paynd.study.easypark.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET

interface CitiesApiService {
    @GET("/cities")
    fun getCities() : CitiesResponse
}

fun createCitiesApiService() : CitiesApiService{
    val okHttpClient = OkHttpClient.Builder().build()
    val json = Json(Json) {
        ignoreUnknownKeys = true
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://pgroute-staging.easyparksystem.net/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .build()

    return retrofit.create(CitiesApiService::class.java)
}