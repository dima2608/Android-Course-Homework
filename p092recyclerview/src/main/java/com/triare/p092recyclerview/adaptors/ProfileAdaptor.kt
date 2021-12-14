package com.triare.p092recyclerview.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triare.p092recyclerview.R
import com.triare.p092recyclerview.User

class ProfileAdaptor(private val items: List<User>): RecyclerView.Adapter<ProfileAdaptor.ProfileViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile, parent, false)
        return ProfileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val userPics = itemView.findViewById<ImageView>(R.id.user_pics)


        fun bind(user: User) {
            setUserPic(user)
        }

        private fun setUserPic(user: User) {
            Glide.with(itemView.context)
                .asBitmap()
                .load(user.avatar)
                .into(userPics)
        }
    }

}