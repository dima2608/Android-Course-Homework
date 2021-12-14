package com.triare.p101weatherforecastapp.ui

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triare.p101weatherforecastapp.R
import com.triare.p101weatherforecastapp.adaptor.HourlyWeatherAdaptor
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

    private var hourlyWeatherAdaptor: HourlyWeatherAdaptor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        observeUpdates()
    }

    private fun initUi() {
        date = findViewById(R.id.date)
        icWeather = findViewById(R.id.ic_weather)
        temp = findViewById(R.id.temperature)
        cloudiness = findViewById(R.id.cloudiness)
        realFeel = findViewById(R.id.real_feel)
        wind = findViewById(R.id.wind)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerViewHourlyWeather = findViewById<RecyclerView>(R.id.recycler_view_hourly_weather)
        recyclerViewHourlyWeather.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        hourlyWeatherAdaptor = HourlyWeatherAdaptor()
        recyclerViewHourlyWeather.adapter = hourlyWeatherAdaptor
    }

    private fun observeUpdates() {
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        viewModel?.hourlyWeatherResult?.observe((this)){
            hourlyWeatherAdaptor?.items = it.data
            hourlyWeatherAdaptor?.notifyDataSetChanged()
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