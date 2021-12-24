package com.triare.p092recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.triare.p092recyclerview.adaptors.SearchAdaptor
import com.triare.p092recyclerview.storage.UserStorage

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerViewSearch = findViewById<RecyclerView>(R.id.recycler_view_search)
        recyclerViewSearch.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewSearch.adapter = SearchAdaptor(UserStorage.random(this))
    }
}