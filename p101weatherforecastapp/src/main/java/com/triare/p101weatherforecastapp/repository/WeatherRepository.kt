package com.triare.p101weatherforecastapp.repository

import com.triare.p101weatherforecastapp.R
import com.triare.p101weatherforecastapp.model.CurrentDto
import java.text.DateFormat
import java.util.*
import kotlin.math.roundToInt

class WeatherRepository() {
    var isDay: Boolean? = null
    fun getDate(): String {
        val calendarDate = Calendar.getInstance().time
        val dateInfo = DateFormat.getDateInstance(DateFormat.FULL).format(calendarDate)
        return dateInfo.substring(0, dateInfo.lastIndexOf(","))
    }

    fun getTemp(dto: CurrentDto): String {
        return dto.data[0]?.temp?.roundToInt().toString()
    }

    fun getRealFeel(dto: CurrentDto): String {
        return "feel like ${dto.data[0]?.appTemp?.roundToInt()}C°"
    }

    fun getWind(dto: CurrentDto): String {
        return "Wind ${dto.data[0]?.windSpd?.roundToInt()} km/h, ${dto.data[0]?.windCdir}"
    }

    fun getCloudiness(dto: CurrentDto): String {
        return dto.data[0]?.weatherCurrentDto!!.description
    }

    fun getWeatherIcon(dto: CurrentDto): Int {
        isDay = dto.data[0]?.pod == "d"

        if (isDay as Boolean) {
            return if (dto.data[0]?.clouds!! <= 10 && dto.data[0]?.rh!! <= 0){
                R.drawable.ic_sunny_24px
            } else if (dto.data[0]?.clouds!! > 10 && dto.data[0]?.rh!! <= 10){
                R.drawable.ic_partly_cloudy_24px
            } else if (dto.data[0]?.clouds!! <= 50 && dto.data[0]?.rh!! > 10) {
                R.drawable.ic_rain_with_clearing_24px
            }else R.drawable.ic_rain_24px
        }
        return R.drawable.ic_night_24px
    }
}