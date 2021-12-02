package com.triare.p092recyclerview.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triare.p092recyclerview.R
import com.triare.p092recyclerview.User

class SearchAdaptor(private val items: List<User>): RecyclerView.Adapter<SearchAdaptor.SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val picture = itemView.findViewById<ImageView>(R.id.picture)

        fun bind(user: User) {
            Glide.with(itemView.context)
                .asBitmap()
                .load(user.avatar)
                .into(picture)
        }
    }

}