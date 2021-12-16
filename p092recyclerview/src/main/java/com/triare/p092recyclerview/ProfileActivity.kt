package com.triare.p092recyclerview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.triare.p092recyclerview.adaptors.ProfileAdaptor
import com.triare.p092recyclerview.storage.UserStorage

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initUi()
    }

    private fun initRecyclerView() {
        val recyclerViewProfile = findViewById<RecyclerView>(R.id.recycler_view_profile)
        recyclerViewProfile.layoutManager = GridLayoutManager(this,3)
        recyclerViewProfile.adapter = ProfileAdaptor(UserStorage.random(this))
    }

    private fun initUi() {
        setAvatar()
        initRecyclerView()
    }

    private fun setAvatar() {
        val avatar = findViewById<ImageView>(R.id.user_avatar)
        if (avatar != null) {
            Glide.with(applicationContext)
                .asBitmap()
                .load(ic_avatar)
                .circleCrop()
                .into(avatar)
        }
    }
}