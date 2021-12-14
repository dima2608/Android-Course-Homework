package com.triare.p091mvvmcatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val rbBtnEurope = findViewById<RadioButton>(R.id.btn_europe)
        val rbBtnAsia = findViewById<RadioButton>(R.id.btn_asia)
        menu?.setGroupVisible(R.id.menu_Europe, rbBtnEurope.isChecked)
        menu?.setGroupVisible(R.id.menu_Asia, rbBtnAsia.isChecked)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel?.getCountry(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun initUi() {
        findViewById<ImageView>(R.id.ic_country).setImageDrawable(getDrawable(R.drawable.ic_eurasia))

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel!!.countryLive.observe(this, Observer {
            if (it != null) {
                findViewById<TextView>(R.id.country_title).setText(it.title)
                findViewById<ImageView>(R.id.ic_country).setImageDrawable(getDrawable(it.flag))
                findViewById<TextView>(R.id.country_description).setText(it.description)
            }
        })
    }
}