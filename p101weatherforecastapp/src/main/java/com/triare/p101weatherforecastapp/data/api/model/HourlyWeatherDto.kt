package com.triare.p101weatherforecastapp.data.api.model

import com.google.gson.annotations.SerializedName

data class HourlyDto(

	@SerializedName("country_code")
	val countryCode: String,

	@SerializedName("city_name")
	val cityName: String,

	@SerializedName("data")
	val data: List<DataItemHourlyDto>,

	@SerializedName("timezone")
	val timezone: String,

	@SerializedName("lon")
	val lon: Double,

	@SerializedName("state_code")
	val stateCode: String,

	@SerializedName("lat")
	val lat: Double
)

data class DataItemHourlyDto(

	@SerializedName("sunrise")
	val sunrise: String,

	@SerializedName("pod")
	val pod: String,

	@SerializedName("pres")
	val pres: Double,

	@SerializedName("timezone")
	val timezone: String,

	@SerializedName("ob_time")
	val obTime: String,

	@SerializedName("wind_cdir")
	val windCdir: String,

	@SerializedName("lon")
	val lon: Double,

	@SerializedName("clouds")
	val clouds: Int,

	@SerializedName("wind_spd")
	val windSpd: Double,

	@SerializedName("city_name")
	val cityName: String,

	@SerializedName("h_angle")
	val hAngle: Int,

	@SerializedName("datetime")
	val datetime: String,

	@SerializedName("precip")
	val precip: Double,

	@SerializedName("weather")
	val weatherHourlyDto: WeatherHourlyDto,

	@SerializedName("station")
	val station: String,

	@SerializedName("elev_angle")
	val elevAngle: Double,

	@SerializedName("dni")
	val dni: Double,

	@SerializedName("lat")
	val lat: Double,

	@SerializedName("vis")
	val vis: Double,

	@SerializedName("uv")
	val uv: Double,

	@SerializedName("temp")
	val temp: Double,

	@SerializedName("dhi")
	val dhi: Double,

	@SerializedName("ghi")
	val ghi: Double,

	@SerializedName("app_temp")
	val appTemp: Double,

	@SerializedName("dewpt")
	val dewpt: Double,

	@SerializedName("wind_dir")
	val windDir: Int,

	@SerializedName("solar_rad")
	val solarRad: Double,

	@SerializedName("country_code")
	val countryCode: String,

	@SerializedName("rh")
	val rh: Double,

	@SerializedName("slp")
	val slp: Double,

	@SerializedName("snow")
	val snow: Double,

	@SerializedName("sunset")
	val sunset: String,

	@SerializedName("aqi")
	val aqi: Int,

	@SerializedName("state_code")
	val stateCode: String,

	@SerializedName("wind_cdir_full")
	val windCdirFull: String,

	@SerializedName("ts")
	val ts: Int
)

data class WeatherHourlyDto(

	@SerializedName("code")
	val code: Int,

	@SerializedName("icon")
	val icon: String,

	@SerializedName("description")
	val description: String
)
