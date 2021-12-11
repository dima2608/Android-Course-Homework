package com.triare.p101weatherforecastapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triare.p101weatherforecastapp.Const.BASE_URL
import com.triare.p101weatherforecastapp.api.WeatherService
import com.triare.p101weatherforecastapp.model.CurrentDto
import com.triare.p101weatherforecastapp.model.HourlyDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel : ViewModel() {

    private val _currentWeatherResult = MutableLiveData<CurrentDto>()
    val currentWeatherResult: LiveData<CurrentDto> = _currentWeatherResult

    private val _hourlyWeatherResult = MutableLiveData<HourlyDto>()
    val hourlyWeatherResult: LiveData<HourlyDto> = _hourlyWeatherResult

    init {
        getCurrentWeather()
        getHourlyWeather()
    }

    private fun getCurrentWeather() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val currentWeatherService = retrofit.create(WeatherService::class.java)

        currentWeatherService.getCurrent(32.05f,49.44f, "metric").enqueue(object : Callback<CurrentDto>{
            override fun onResponse(call: Call<CurrentDto>, response: Response<CurrentDto>) {
                _currentWeatherResult.value = response.body()
                Log.d("currentReq", response.code().toString())
            }

            override fun onFailure(call: Call<CurrentDto>, t: Throwable) {
                Log.d("reqErr", t.message.toString())
                t.printStackTrace()
            }
        })
    }

    private fun getHourlyWeather() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val hourlyWeatherService = retrofit.create(WeatherService::class.java)

        hourlyWeatherService.getHourly(32.05f,49.44f,24,"metric").enqueue(object : Callback<HourlyDto>{
            override fun onResponse(call: Call<HourlyDto>, response: Response<HourlyDto>) {
                _hourlyWeatherResult.value = response.body()
                Log.d("hourlyReq", response.code().toString())
            }

            override fun onFailure(call: Call<HourlyDto>, t: Throwable) {
                Log.d("reqErr", t.message.toString())
                t.printStackTrace()
            }
        })
    }


}