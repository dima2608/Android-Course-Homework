package com.triare.p101weatherforecastapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.triare.p101weatherforecastapp.data.api.BASE_URL
import com.triare.p101weatherforecastapp.R
import com.triare.p101weatherforecastapp.data.api.WeatherService
import com.triare.p101weatherforecastapp.data.api.model.CurrentDto
import com.triare.p101weatherforecastapp.data.api.model.DataItemCurrentDto
import com.triare.p101weatherforecastapp.data.api.model.HourlyDto
import com.triare.p101weatherforecastapp.ui.model.CurrentWeatherDvo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.util.*
import kotlin.math.roundToInt

class WeatherRepository {
    private val currentWeather = MutableLiveData<CurrentWeatherDvo>()
    private val hourlyWeather = MutableLiveData<HourlyDto>()

    private fun getDate(): String {
        val calendarDate = Calendar.getInstance().time
        val dateInfo = DateFormat.getDateInstance(DateFormat.FULL).format(calendarDate)
        return dateInfo.substring(0, dateInfo.lastIndexOf(","))
    }

    private fun getTemp(dto: DataItemCurrentDto?): String {
        return dto?.temp?.roundToInt().toString()
    }

    private fun getRealFeel(dto: CurrentDto): String {
        val realFeel: String = dto.data[0]?.appTemp?.roundToInt().toString()
        return "feel like $realFeel C°"
    }

    private fun getWind(dto: CurrentDto): String {
        val windSpd: String = dto.data[0]?.windSpd?.roundToInt().toString()
        val windCdir = dto.data[0]?.windCdir
        return "Wind $windSpd km/h, $windCdir"
    }

    private fun getCloudiness(dto: CurrentDto): String {
        return dto.data[0]?.weatherCurrentDto!!.description
    }

    private fun getWeatherIcon(dto: CurrentDto): Int {
        val isDay = dto.data[0]?.pod == "d"

        if (isDay) {
            return if (dto.data[0]?.clouds!! <= 10 && dto.data[0]?.rh!! <= 0) {
                R.drawable.ic_sunny_24px
            } else if (dto.data[0]?.clouds!! > 10 && dto.data[0]?.rh!! <= 10) {
                R.drawable.ic_partly_cloudy_24px
            } else if (dto.data[0]?.clouds!! <= 50 && dto.data[0]?.rh!! > 10) {
                R.drawable.ic_rain_with_clearing_24px
            } else R.drawable.ic_rain_24px
        }
        return R.drawable.ic_night_24px
    }

    fun getCurrentWeather(): MutableLiveData<CurrentWeatherDvo> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val currentWeatherService = retrofit.create(WeatherService::class.java)

        currentWeatherService.getCurrent(LON, LAT, "metric")
            .enqueue(object : Callback<CurrentDto> {
                override fun onResponse(call: Call<CurrentDto>, response: Response<CurrentDto>) {
                    val body = response.body()
                    currentWeather.value = CurrentWeatherDvo(
                        date = getDate(),
                        temp = getTemp(body?.data?.get(0)),
                        realFeel = body?.let { getRealFeel(it) }.orEmpty(),
                        wind = body?.let { getWind(it) }.orEmpty(),
                        cloudiness = body?.let { getCloudiness(it) }.orEmpty(),
                        ic_Weather = body?.let { getWeatherIcon(it) }
                    )
                }

                override fun onFailure(call: Call<CurrentDto>, t: Throwable) {
                    Log.d("reqErr", t.message.toString())
                    t.printStackTrace()
                }
            })
        return currentWeather
    }


    fun getHourlyWeather(): MutableLiveData<HourlyDto> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val hourlyWeatherService = retrofit.create(WeatherService::class.java)

        hourlyWeatherService.getHourly(LON, LAT, 24, "metric")
            .enqueue(object : Callback<HourlyDto> {
                override fun onResponse(call: Call<HourlyDto>, response: Response<HourlyDto>) {
                    hourlyWeather.value = response.body()
                }

                override fun onFailure(call: Call<HourlyDto>, t: Throwable) {
                    Log.d("reqHErr", t.message.toString())
                    t.printStackTrace()
                }
            })
        return hourlyWeather
    }

    companion object {
        private const val LON = 32.05f
        private const val LAT = 49.44f
    }
}