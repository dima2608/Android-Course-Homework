package com.triare.p101weatherforecastapp.ui

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
import com.triare.p101weatherforecastapp.adaptors.HourlyWeatherAdaptor
import com.triare.p101weatherforecastapp.viewmodels.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private var viewModel: WeatherViewModel? = null
    private var date: TextView? = null
    private var icWeather: ImageView? = null
    private var temp: TextView? = null
    private var cloudiness: TextView? = null
    private var realFeel: TextView? = null
    private var wind: TextView? = null
    private var hourlyAdaptor: HourlyWeatherAdaptor? = null

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
        initRecycler()
    }

    private fun initRecycler() {
        val recyclerHourlyWeather = findViewById<RecyclerView>(R.id.recycler_view_hourly_weather)
        recyclerHourlyWeather.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        hourlyAdaptor = HourlyWeatherAdaptor()
        recyclerHourlyWeather.adapter = hourlyAdaptor
    }

    private fun observeUpdates() {
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        viewModel?.getHourlyWeatherResultLiveData()?.observe(this) {
            hourlyAdaptor?.items = it.data
            hourlyAdaptor?.notifyDataSetChanged()
        }


        viewModel?.getCurrentWeatherResultLiveData()?.observe(this) {
            val spannableRealFeelString = SpannableString(it.realFeel)
            val spannableWindString = SpannableString(it.wind)
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

            date?.text = it.date
            temp?.text = it.temp
            realFeel?.text = spannableRealFeelString
            wind?.setTextColor(Color.GRAY)
            wind?.text = spannableWindString
            cloudiness?.text = it.cloudiness
            it.ic_Weather?.let { icon -> icWeather?.setImageResource(icon) }
        }
    }

}