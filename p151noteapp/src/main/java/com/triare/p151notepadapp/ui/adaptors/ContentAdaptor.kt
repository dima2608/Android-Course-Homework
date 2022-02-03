package com.triare.p151notepadapp.ui.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.triare.p151notepadapp.R
import com.triare.p151notepadapp.ui.dvo.ContentDvo

class ContentAdaptor(
    private val items: List<ContentDvo>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ContentAdaptor.ContentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_content, parent, false)
        return ContentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val title = itemView.findViewById<TextView>(R.id.title_item)
        private val dataTime = itemView.findViewById<TextView>(R.id.time_when)
        private val fractions = itemView.findViewById<TextView>(R.id.fractions)

        init {
            itemView.setOnClickListener(this)
        }


        fun bind(data: ContentDvo) {
            title.text = data.title
            dataTime.text = data.dataTime
            fractions.text = data.fractions

        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, contentId = items[position].contentId)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, contentId: Long)
    }

}