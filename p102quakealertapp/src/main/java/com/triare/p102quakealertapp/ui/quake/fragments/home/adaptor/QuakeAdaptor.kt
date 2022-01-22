package com.triare.p102quakealertapp.ui.quake.fragments.home.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.triare.p102quakealertapp.R
import com.triare.p102quakealertapp.ui.quake.dvo.FeatureQuakeDvo


class QuakeAdaptor(
    private val items: List<FeatureQuakeDvo>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<QuakeAdaptor.QuakeViewHolder>() {
    //var items: List<FeatureQuakeDvo> = listOf()

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


        fun bind(data: FeatureQuakeDvo) {
            time.text = data.dateTime
            location.text = data.location
            intensity.setText(data.intensityTitle)
            intensity.setBackgroundResource(data.intensityColor)
            magnitude.text = data.magnitude

        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, data = items[position])
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, data: FeatureQuakeDvo)
    }
}