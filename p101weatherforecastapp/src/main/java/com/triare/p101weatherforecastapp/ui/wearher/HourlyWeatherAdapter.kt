package com.triare.p101weatherforecastapp.ui.wearher

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.triare.p101weatherforecastapp.R
import com.triare.p101weatherforecastapp.data.api.model.DataItemHourlyDto


class HourlyWeatherAdapter :
    RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherViewHolder>() {
    var items: List<DataItemHourlyDto> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hourly_weather, parent, false)
        return HourlyWeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        holder.bind(items[position])
        Log.d(TAG, items[position].toString())
    }

    override fun getItemCount() = items.size

    inner class HourlyWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val time = itemView.findViewById<TextView>(R.id.time)
        private val hourlyTemp = itemView.findViewById<TextView>(R.id.hourly_temp)

        fun bind(data: DataItemHourlyDto) {
            time.text = data.datetime
            hourlyTemp.text = "${data.temp} °"
            hourlyTemp.setCompoundDrawablesWithIntrinsicBounds(getWeatherIcon(data), 0, 0, 0)
        }

        private fun getWeatherIcon(dto: DataItemHourlyDto): Int {
            val isDay = dto.pod == "d"

            if (isDay) {
                return if (dto.clouds <= 10 && dto.rh <= 0) {
                    R.drawable.ic_sunny_24px
                } else if (dto.clouds > 10 && dto.rh <= 10) {
                    R.drawable.ic_partly_cloudy_24px
                } else if (dto.clouds <= 50 && dto.rh > 10) {
                    R.drawable.ic_rain_with_clearing_24px
                } else R.drawable.ic_rain_24px
            }
            return R.drawable.ic_night_24px
        }
    }

    companion object {
        private val TAG: String = HourlyWeatherAdapter::class.java.simpleName
    }

}