package com.triare.p092recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triare.p092recyclerview.adaptors.FeedAdaptor
import com.triare.p092recyclerview.adaptors.StoriesAdaptor
import com.triare.p092recyclerview.storage.UserStorage

class MainActivity : AppCompatActivity() {

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
    }

    private fun initUi() {
        initRecyclerView()
        bottomNav()
    }

    private fun initRecyclerView() {
        val recyclerViewStories = findViewById<RecyclerView>(R.id.recycler_view_stories)
        val recyclerViewFeed = findViewById<RecyclerView>(R.id.recycler_view_feed)

        recyclerViewStories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewStories.adapter = StoriesAdaptor(UserStorage.random(this))

        recyclerViewFeed.layoutManager = LinearLayoutManager(this)
        recyclerViewFeed.adapter = FeedAdaptor(UserStorage.random(this))
    }

    private fun bottomNav() {
        val bottomNavBtn = listOf<ImageView>(
            findViewById(R.id.btn_search),
            findViewById(R.id.btn_chat),
            findViewById(R.id.btn_profile),
        )
        bottomNavBtn.forEach { btn ->
            btn.setOnClickListener{
                when(btn.id){
                    R.id.btn_search -> {
                        val searchIntent = Intent(this, SearchActivity::class.java)
                        startForResult.launch(searchIntent)
                    }
                    R.id.btn_chat -> {
                        val directIntent = Intent(this, DirectActivity::class.java)
                        startForResult.launch(directIntent)
                    }
                    R.id.btn_profile -> {
                        val profileIntent = Intent(this, ProfileActivity::class.java)
                        startForResult.launch(profileIntent)
                    }
                }
            }
        }
    }

}
