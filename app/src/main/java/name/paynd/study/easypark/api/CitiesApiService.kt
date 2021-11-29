package name.paynd.study.easypark.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface CitiesApiService {
    @GET("/cities")
    suspend fun getCities(): CitiesResponse
}

fun createCitiesApiService(): CitiesApiService {
    val okHttpClient = OkHttpClient.Builder().build()

    val moshi = Moshi.Builder().build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://pgroute-staging.easyparksystem.net/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    return retrofit.create(CitiesApiService::class.java)
}