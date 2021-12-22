package com.triare.p102quakealertapp.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.triare.p102quakealertapp.R
import com.triare.p102quakealertapp.model.FeaturesItem

class QuakeAdaptor(): RecyclerView.Adapter<QuakeAdaptor.QuakeViewHolder>() {
    var items: List<FeaturesItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuakeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quake, parent, false)
        return QuakeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuakeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class QuakeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val time = itemView.findViewById<TextView>(R.id.time_when_quake)
        private val location = itemView.findViewById<TextView>(R.id.location)
        private val intensity = itemView.findViewById<TextView>(R.id.quake_intensity)
        private val magnitude = itemView.findViewById<TextView>(R.id.magnitude)

        fun bind(data: FeaturesItem) {

        }
    }
}