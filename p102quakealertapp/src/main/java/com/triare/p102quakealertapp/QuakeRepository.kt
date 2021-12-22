package com.triare.p102quakealertapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.triare.p102quakealertapp.api.QuakeService
import com.triare.p102quakealertapp.model.QuakeDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuakeRepository {
    val quakeResult = MutableLiveData<QuakeDto>()


     fun getQuakeDto(): MutableLiveData<QuakeDto> {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val quakeService = retrofit.create(QuakeService::class.java)

        quakeService.getCurrentQuakes(3).enqueue(object : Callback<QuakeDto> {
            override fun onResponse(call: Call<QuakeDto>, response: Response<QuakeDto>) {
                quakeResult.value = response.isSuccessful.let { response.body() }
                Log.d("RespCode", response.code().toString())
                Log.d("INITEDinRes" ,quakeResult.value.toString())
            }
            override fun onFailure(call: Call<QuakeDto>, t: Throwable) {
                Log.d("Error", "Error Quake Call")
                t.printStackTrace()
            }
        })
         return quakeResult
    }
    companion object {
        private const val BASE_URL = "https://api.geonet.org.nz/"
    }
}