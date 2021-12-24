package com.triare.p101weatherforecastapp.storage

data class CurrentWeatherDvo(
    var date: String,
    var temp: String,
    var realFeel: String,
    var wind: String,
    var cloudiness: String,
    var ic_Weather: Int?
)


