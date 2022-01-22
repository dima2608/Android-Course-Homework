package com.triare.p102quakealertapp.data.api

import com.triare.p102quakealertapp.data.api.model.QuakeDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuakeService {

    @GET("quake")
    fun getCurrentQuakes(
        @Query("MMI") MMI: Int
    ): Call<QuakeDto>
}