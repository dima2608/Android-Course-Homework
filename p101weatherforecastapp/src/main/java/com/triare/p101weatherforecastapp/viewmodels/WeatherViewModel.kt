package com.triare.p101weatherforecastapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triare.p101weatherforecastapp.models.HourlyDto
import com.triare.p101weatherforecastapp.repository.WeatherRepository
import com.triare.p101weatherforecastapp.storage.CurrentWeatherDvo


class WeatherViewModel : ViewModel() {

    private var currentWeatherResult = MutableLiveData<CurrentWeatherDvo>()
    private var hourlyWeatherResult = MutableLiveData<HourlyDto>()
    private val weatherRepository = WeatherRepository()

    init {
        currentWeatherResult = weatherRepository.getCurrentWeather()
        hourlyWeatherResult =  weatherRepository.getHourlyWeather()
    }

    fun getCurrentWeatherResultLiveData(): MutableLiveData<CurrentWeatherDvo> {
        return currentWeatherResult
    }

    fun getHourlyWeatherResultLiveData(): MutableLiveData<HourlyDto> {
        return hourlyWeatherResult
    }



}