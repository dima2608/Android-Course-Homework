package com.triare.fragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.triare.fragments.seasons.Season

class MainActivity : AppCompatActivity() {

    private var nextButton: Button? = null
    private var season = Season.WINTER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        updateFragment()
    }

    private fun initUi() {
        nextButton = findViewById(R.id.next_button)
        nextButton?.setOnClickListener { onNextSeason() }
    }

    private fun onNextSeason() {
        setNextSeason()
        updateFragment()
    }

    private fun setNextSeason() {
        season = season.next
    }

    private fun updateFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, season.fragment)
            .commit()
    }
}