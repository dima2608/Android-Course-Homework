package com.triare.p102quakealertapp.api

import com.triare.p102quakealertapp.model.QuakeDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuakeService {

    @GET("quake")
    fun getCurrentQuakes(
        @Query("MMI") MMI: Int
    ): Call<QuakeDto>
}