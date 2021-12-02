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

class DirectAdaptor(private val items: List<User>): RecyclerView.Adapter<DirectAdaptor.DirectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_direct, parent, false)
        return DirectViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DirectViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class DirectViewHolder(items: View): RecyclerView.ViewHolder(items) {

        private val avatar = itemView.findViewById<ImageView>(R.id.avatar)
        private val isOnline = itemView.findViewById<TextView>(R.id.isOnline)
        private val name = itemView.findViewById<TextView>(R.id.user_name_in_direct)
        private val message = itemView.findViewById<TextView>(R.id.message)

        fun bind(user: User) {
            Glide.with(itemView.context)
                .asBitmap()
                .load(user.avatar)
                .circleCrop()
                .into(avatar)

            when(user.isOnline){
                true -> {
                    isOnline.text = "Online"
                }
                false -> {
                    isOnline.text = "Offline"
                }
            }

            message.text = user.massage
            name.text = user.name
        }
    }
}