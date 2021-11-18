package com.triare.p081userdata

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable

import kotlinx.parcelize.Parcelize
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class Country : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        val ua = Countries("Ukraine", R.drawable.ic_ua, R.id.btn_country_ua)
        val gb = Countries("United Kingdom", R.drawable.ic_gb, R.id.btn_country_uk)
        val no = Countries("Norway", R.drawable.ic_no, R.id.btn_country_no)
        val au = Countries("Australia", R.drawable.ic_au, R.id.btn_country_au)
        val jp = Countries("Japan", R.drawable.ic_jp, R.id.btn_country_jp)

        val btnGoBack = findViewById<ImageButton>(R.id.btn_go_back_country)

        val countryList = listOf(ua, gb, no, au, jp)

        countryList.forEach { country ->
            findViewById<TextView>(country.id).setOnClickListener{
                setResult(REQUEST_GET_COUNTRY, Intent().putExtra(KEY_COUNTRY, country))
                finish()
            }
        }

        btnGoBack.setOnClickListener {
            finish()
        }

    }
    companion object {
        const val KEY_COUNTRY = "KEY_COUNTRY_COUNTRY"
        const val REQUEST_GET_COUNTRY = 3
    }
}

@Parcelize
data class Countries(
    val name: String,
    val flag: Int,
    val id: Int) : Parcelable