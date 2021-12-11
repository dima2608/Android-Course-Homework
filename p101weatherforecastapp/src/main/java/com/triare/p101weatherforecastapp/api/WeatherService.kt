package com.triare.p101weatherforecastapp.api

import com.triare.p101weatherforecastapp.Const.KEY_API
import com.triare.p101weatherforecastapp.model.CurrentDto
import com.triare.p101weatherforecastapp.model.HourlyDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherService {

    @Headers(KEY_API)

    @GET("current")
    fun getCurrent(
        @Query("lon") lon: Float,
        @Query("lat") lat: Float,
        @Query("units") units: String
    ): Call<CurrentDto>


    @Headers(KEY_API)
    @GET("forecast/hourly")
    fun getHourly(
        @Query("lon") lon: Float,
        @Query("lat") lat: Float,
        @Query("hours") hours: Int,
        @Query("units") units: String
    ):Call<HourlyDto>

}
