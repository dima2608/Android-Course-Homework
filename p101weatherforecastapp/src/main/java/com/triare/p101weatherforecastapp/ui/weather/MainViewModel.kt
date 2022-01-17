package com.triare.p101weatherforecastapp.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triare.p101weatherforecastapp.data.api.model.HourlyDto
import com.triare.p101weatherforecastapp.data.repository.WeatherRepository
import com.triare.p101weatherforecastapp.ui.model.CurrentWeatherDvo

class MainViewModel : ViewModel() {

    private var _currentWeatherResult = MutableLiveData<CurrentWeatherDvo>()
    var currentWeatherResult: LiveData<CurrentWeatherDvo> = _currentWeatherResult

    private var _hourlyWeatherResult = MutableLiveData<HourlyDto>()
    var hourlyWeatherResult: LiveData<HourlyDto> = _hourlyWeatherResult

    private val weatherRepository = WeatherRepository()

    init {
        currentWeatherResult = weatherRepository.getCurrentWeather()
        hourlyWeatherResult =  weatherRepository.getHourlyWeather()
    }


}