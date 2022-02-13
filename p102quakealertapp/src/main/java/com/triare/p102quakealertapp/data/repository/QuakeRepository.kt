package com.triare.p102quakealertapp.data.repository

import android.util.Log
import com.triare.p102quakealertapp.data.api.BASE_URL
import com.triare.p102quakealertapp.data.api.MMI
import com.triare.p102quakealertapp.data.api.QuakeService
import com.triare.p102quakealertapp.data.api.model.QuakeDto
import com.triare.p102quakealertapp.data.mapper.FeatureQuakeMapper
import com.triare.p102quakealertapp.data.mapper.MapsMapper
import com.triare.p102quakealertapp.ui.dvo.FeatureQuakeDvo
import com.triare.p102quakealertapp.ui.dvo.MapsDvo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuakeRepository {

    fun getQuake(result: (List<FeatureQuakeDvo>) -> Unit) {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val quakeService = retrofit.create(QuakeService::class.java)

        quakeService.getCurrentQuakes(MMI).enqueue(object : Callback<QuakeDto> {
            override fun onResponse(call: Call<QuakeDto>, response: Response<QuakeDto>) {
                if (response.isSuccessful) {
                    response.body()?.let { result(FeatureQuakeMapper(it).map()) }
                }
                Log.d("RespCode", response.code().toString())
            }

            override fun onFailure(call: Call<QuakeDto>, t: Throwable) {
                Log.d("Error", "Error Quake Call")
                t.printStackTrace()
            }
        })
    }

    fun getMapsQuake(result: (List<MapsDvo>) -> Unit) {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val quakeService = retrofit.create(QuakeService::class.java)

        quakeService.getCurrentQuakes(MMI).enqueue(object : Callback<QuakeDto> {
            override fun onResponse(call: Call<QuakeDto>, response: Response<QuakeDto>) {
                if (response.isSuccessful) {
                    response.body()?.let { result(MapsMapper(it).map()) }
                }
                Log.d("RespCode", response.code().toString())
            }

            override fun onFailure(call: Call<QuakeDto>, t: Throwable) {
                Log.d("Error", "Error Quake Call")
                t.printStackTrace()
            }
        })
    }
}