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

class StoriesAdaptor(private val items: List<User>) : RecyclerView.Adapter<StoriesAdaptor.StoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stories, parent, false)
        return StoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class StoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val avatar = itemView.findViewById<ImageView>(R.id.ic_stories)
        private val name = itemView.findViewById<TextView>(R.id.user_name)

        fun bind(user: User) {
            Glide.with(itemView.context)
                .asBitmap()
                .load(user.avatar)
                .circleCrop()
                .into(avatar)

            name.text = user.name
        }
    }
}