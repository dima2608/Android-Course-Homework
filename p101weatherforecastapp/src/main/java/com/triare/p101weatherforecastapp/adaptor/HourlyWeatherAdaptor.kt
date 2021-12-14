package com.triare.p101weatherforecastapp.adaptor

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.triare.p101weatherforecastapp.R
import com.triare.p101weatherforecastapp.model.CurrentDto
import com.triare.p101weatherforecastapp.model.DataItemHourlyDto
import com.triare.p101weatherforecastapp.model.HourlyDto
import com.triare.p101weatherforecastapp.repository.WeatherRepository
import java.text.DateFormat
import java.util.*
import kotlin.math.roundToInt

class HourlyWeatherAdaptor(): RecyclerView.Adapter<HourlyWeatherAdaptor.HourlyWeatherViewHolder>() {
    var items: List<DataItemHourlyDto> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hourly_weather, parent, false)
        return HourlyWeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class HourlyWeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val time = itemView.findViewById<TextView>(R.id.time)
        private val hourlyTemp = itemView.findViewById<TextView>(R.id.hourly_temp)

        fun bind(data: DataItemHourlyDto) {
            time.text = data.clouds.toString()
            Log.d("WTF", data.clouds.toString())
        }
    }
}