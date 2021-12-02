package com.triare.p092recyclerview

import android.os.Bundle
import android.widget.ImageView
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

        val recyclerViewProfile = findViewById<RecyclerView>(R.id.recycler_view_profile)
        val avatar = findViewById<ImageView>(R.id.user_avatar)

        recyclerViewProfile.layoutManager = GridLayoutManager(this,3)
        recyclerViewProfile.adapter = ProfileAdaptor(UserStorage.random(this))


        Glide.with(applicationContext)
            .asBitmap()
            .load("https://1.bp.blogspot.com/-wcGBBlbjSgY/XyOHzJP5hmI/AAAAAAAAFXA/pj-_e6iUCYUXaBqFYFnaLBJ2Z3-A6hz3ACLcBGAsYHQ/s750/2.jpg")
            .circleCrop()
            .into(avatar)
    }
}