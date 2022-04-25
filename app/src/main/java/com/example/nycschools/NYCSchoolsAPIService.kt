package com.example.nycschools

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val BASE_URL = "https://data.cityofnewyork.us"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface NYCSchoolsAPIService {
    /**
     * Returns a Retrofit callback that delivers a Schools list
     */
    @Headers("Content-Type: application/json")
    @GET("$BASE_URL/resource/s3k6-pzi2")
    fun getSchools():
            Deferred<List<School>>

    @Headers("Content-Type: application/json")
    @GET("$BASE_URL/resource/f9bf-2cp4")
    fun getSat(@Query("dbn") dbn: String):
            Deferred<List<SchoolSatDetails>>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object SchoolsApi {
    val retrofitService: NYCSchoolsAPIService by lazy { retrofit.create(NYCSchoolsAPIService::class.java) }
}