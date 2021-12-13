package com.triare.p101weatherforecastapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triare.p101weatherforecastapp.adaptor.HourlyWeatherAdaptor
import com.triare.p101weatherforecastapp.model.DataItemHourlyDto
import com.triare.p101weatherforecastapp.model.HourlyDto
import com.triare.p101weatherforecastapp.repository.WeatherRepository

class MainActivity : AppCompatActivity() {

    private var viewModel: WeatherViewModel? = null
    private val weatherRepository = WeatherRepository()

    private var date: TextView? = null
    private var icWeather: ImageView? = null
    private var temp: TextView? = null
    private var cloudiness: TextView? = null
    private var realFeel: TextView? = null
    private var wind: TextView? = null
    private var hourlyWeather: HourlyDto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        val recyclerViewHourlyWeather = findViewById<RecyclerView>(R.id.recycler_view_hourly_weather)
        initUi()
        observeUpdates()

        recyclerViewHourlyWeather.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewHourlyWeather.adapter = hourlyWeather?.let { HourlyWeatherAdaptor(it.data) }

    }

    private fun initUi() {
        date = findViewById(R.id.date)
        icWeather = findViewById(R.id.ic_weather)
        temp = findViewById(R.id.temperature)
        cloudiness = findViewById(R.id.cloudiness)
        realFeel = findViewById(R.id.real_feel)
        wind = findViewById(R.id.wind)
    }

    private fun observeUpdates() {
        viewModel?.hourlyWeatherResult?.observe((this)){
            hourlyWeather = it
            Log.d("norm", it.data.size.toString())
        }
        viewModel?.currentWeatherResult?.observe(this) {
            val spannableRealFeelString = SpannableString(weatherRepository.getRealFeel(it))
            val spannableWindString = SpannableString(weatherRepository.getWind(it))
            val colorGray = ForegroundColorSpan(Color.GRAY)
            val colorWhite = ForegroundColorSpan(Color.WHITE)

            spannableRealFeelString.setSpan(
                colorGray,
                0,
                spannableRealFeelString.lastIndexOf(" "),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

            spannableWindString.setSpan(
                colorWhite,
                spannableWindString.indexOf(" "),
                spannableWindString.lastIndexOf(","),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

            date?.text = weatherRepository.getDate()
            temp?.text = weatherRepository.getTemp(it)
            realFeel?.text = spannableRealFeelString
            wind?.setTextColor(Color.GRAY)
            wind?.text = spannableWindString
            cloudiness?.text = weatherRepository.getCloudiness(it)
            icWeather?.setImageResource(weatherRepository.getWeatherIcon(it))
        }
    }

}