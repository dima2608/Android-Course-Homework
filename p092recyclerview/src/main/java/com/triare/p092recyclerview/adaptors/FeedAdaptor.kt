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

class FeedAdaptor(private val items: List<User>): RecyclerView.Adapter<FeedAdaptor.FeedViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feed, parent, false)
        return FeedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class FeedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val avatar = itemView.findViewById<ImageView>(R.id.ic_user_prof_pic)
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val feed = itemView.findViewById<ImageView>(R.id.feed)

        fun bind(user: User) {
            setAvatar(user)
            name.text = user.name
            setFeed(user)
        }

        private fun setFeed(user: User) {
            Glide.with(itemView.context)
                .asBitmap()
                .load(user.feed)
                .into(feed)
        }

        private fun setAvatar(user: User) {
            Glide.with(itemView.context)
                .asBitmap()
                .load(user.avatar)
                .circleCrop()
                .into(avatar)
        }
    }
}