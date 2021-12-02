package com.triare.p092recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triare.p092recyclerview.adaptors.DirectAdaptor
import com.triare.p092recyclerview.adaptors.StoriesAdaptor
import com.triare.p092recyclerview.storage.UserStorage

class DirectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direct)

        val recyclerViewDirect = findViewById<RecyclerView>(R.id.recycler_view_direct)

        recyclerViewDirect.layoutManager = LinearLayoutManager(this)
        recyclerViewDirect.adapter = DirectAdaptor(UserStorage.random(this))

    }
}