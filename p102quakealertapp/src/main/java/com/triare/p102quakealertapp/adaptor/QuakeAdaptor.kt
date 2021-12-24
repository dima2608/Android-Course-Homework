package com.triare.p102quakealertapp.adaptor

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.triare.p102quakealertapp.R
import com.triare.p102quakealertapp.model.FeaturesItem


class QuakeAdaptor(
    private val items: List<FeaturesItem>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<QuakeAdaptor.QuakeViewHolder>() {
    //var items: List<FeaturesItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuakeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quake, parent, false)
        return QuakeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuakeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class QuakeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val time = itemView.findViewById<TextView>(R.id.time_when_quake)
        private val location = itemView.findViewById<TextView>(R.id.location)
        private val intensity = itemView.findViewById<TextView>(R.id.quake_intensity)
        private val magnitude = itemView.findViewById<TextView>(R.id.magnitude)

        init {
            itemView.setOnClickListener(this)
        }


        @SuppressLint("ResourceType")
        fun bind(data: FeaturesItem) {
            val intensityData = initIntensity(data.properties.magnitude)
            time.text = data.properties.time
            location.text = data.properties.locality
            intensity.setText(intensityData.title)
            intensity.setBackgroundResource(intensityData.color)
            magnitude.text = String.format("%.2f", data.properties.magnitude)

        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, data = items[position])
                Log.d("TEST", items[position].toString())
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, data: FeaturesItem)
    }

    companion object {
        fun initIntensity(intensity: Double): QuakeIntensity {
            return when (intensity) {
                in 1.0..1.99 -> QuakeIntensity.BARELY_NOTICEABLE
                in 2.0..2.99 -> QuakeIntensity.WEAK
                in 3.0..3.99 -> QuakeIntensity.AVERAGE
                in 4.0..4.99 -> QuakeIntensity.STRONG
                else -> QuakeIntensity.VERY_STRONG
            }
        }
    }
}