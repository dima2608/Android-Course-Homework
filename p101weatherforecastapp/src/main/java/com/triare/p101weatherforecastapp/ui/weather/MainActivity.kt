package com.triare.p101weatherforecastapp.ui.weather

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triare.p101weatherforecastapp.R
import com.triare.p101weatherforecastapp.data.api.model.HourlyDto
import com.triare.p101weatherforecastapp.ui.model.CurrentWeatherDvo

class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null
    private var date: TextView? = null
    private var icWeather: ImageView? = null
    private var temp: TextView? = null
    private var cloudiness: TextView? = null
    private var realFeel: TextView? = null
    private var wind: TextView? = null
    private var hourlyAdaptor: HourlyWeatherAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        initViewModel()
        observeUpdates()
    }

    private fun initUi() {
        date = findViewById(R.id.date)
        icWeather = findViewById(R.id.ic_weather)
        temp = findViewById(R.id.temperature)
        cloudiness = findViewById(R.id.cloudiness)
        realFeel = findViewById(R.id.real_feel)
        wind = findViewById(R.id.wind)
        initRecycler()
    }

    private fun initRecycler() {
        val recyclerHourlyWeather = findViewById<RecyclerView>(R.id.recycler_view_hourly_weather)
        recyclerHourlyWeather.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        hourlyAdaptor = HourlyWeatherAdapter()
        recyclerHourlyWeather.adapter = hourlyAdaptor
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun observeUpdates() {
        observeHourlyWeatherResult()
        observeCurrentWeatherResult()
    }

    private fun observeHourlyWeatherResult() {
        viewModel?.hourlyWeatherResult?.observe(this) {
            renderHourlyWeather(it)
        }
    }


    private fun observeCurrentWeatherResult() {
        viewModel?.currentWeatherResult?.observe(this) {
            renderCurrentWeather(it)
        }
    }

    private fun renderHourlyWeather(it: HourlyDto) {
        hourlyAdaptor?.items = it.data
        hourlyAdaptor?.notifyDataSetChanged()
    }

    private fun renderCurrentWeather(currentWeatherDvo: CurrentWeatherDvo) {
        val spannableRealFeelString = SpannableString(currentWeatherDvo.realFeel)
        val spannableWindString = SpannableString(currentWeatherDvo.wind)
        val colorGray = ForegroundColorSpan(Color.GRAY)
        val colorWhite = ForegroundColorSpan(Color.WHITE)
        spannableRealFeelString.setSpan(
            colorGray,
            0,
            spannableRealFeelString.lastIndexOf(" "),
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )

        spannableWindString.setSpan(
            colorWhite,
            spannableWindString.indexOf(" "),
            spannableWindString.lastIndexOf(","),
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )

        date?.text = currentWeatherDvo.date
        temp?.text = currentWeatherDvo.temp
        realFeel?.text = spannableRealFeelString
        wind?.setTextColor(Color.GRAY)
        wind?.text = spannableWindString
        cloudiness?.text = currentWeatherDvo.cloudiness
        currentWeatherDvo.ic_Weather?.let { icon -> icWeather?.setImageResource(icon) }
    }

}