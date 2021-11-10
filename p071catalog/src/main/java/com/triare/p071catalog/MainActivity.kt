package com.triare.p071catalog

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val rbTopMemes = findViewById<RadioButton>(R.id.radio_btn_top_memes)
        val rbTopMemesLastDecadeS = findViewById<RadioButton>(R.id.radio_btn_top_memes_last_decade_s)
        menu?.setGroupVisible(R.id.top_memes, rbTopMemes.isChecked)
        menu?.setGroupVisible(R.id.top_memes_last_decade_s, rbTopMemesLastDecadeS.isChecked)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.top_meme_naw_1 -> {
                setImage(R.drawable.memes_1)
                setText(R.string.such_a_good_meme)
            }
            R.id.top_meme_naw_2 -> {
                setImage(R.drawable.memes_2)
                setText(R.string.such_a_good_meme)
            }
            R.id.top_meme_naw_3 -> {
                setImage(R.drawable.memes_3)
                setText(R.string.such_a_good_meme)
            }
            R.id.top_meme_today_1 -> {
                setImage(R.drawable.memes_4)
                setText(R.string.such_a_good_meme)
            }
            R.id.top_meme_today_2 -> {
                setImage(R.drawable.memes_5)
                setText(R.string.such_a_good_meme)
            }
            R.id.top_meme_today_3 -> {
                setImage(R.drawable.memes_6)
                setText(R.string.such_a_good_meme)
            }
            R.id.top_meme_this_months_1 -> {
                setImage(R.drawable.memes_7)
                setText(R.string.such_a_good_meme)
            }
            R.id.top_meme_this_months_2 -> {
                setImage(R.drawable.memes_8)
                setText(R.string.such_a_good_meme)
            }
            R.id.top_meme_this_months_3 -> {
                setImage(R.drawable.memes_9)
                setText(R.string.such_a_good_meme)
            }
            R.id.fun_meme_1 -> {
                setImage(R.drawable.top_memes_1)
                setText(R.string.not_sure_meme)
            }
            R.id.fun_meme_2 -> {
                setImage(R.drawable.top_memes_2)
                setText(R.string.frog_meme)
            }
            R.id.fun_meme_3 -> {
                setImage(R.drawable.top_memes_3)
                setText(R.string.drake_meme)
            }
            R.id.super_fun_meme_1 -> {
                setImage(R.drawable.top_memes_4)
                setText(R.string.boyfriend_meme)
            }
            R.id.super_fun_meme_2 -> {
                setImage(R.drawable.top_memes_5)
                setText(R.string.such_a_good_meme)
            }
            R.id.super_fun_meme_3 -> {
                setImage(R.drawable.top_memes_6)
                setText(R.string.baby_joda_meme)
            }
            R.id.ultra_fun_meme_1 -> {
                setImage(R.drawable.top_memes_7)
                setText(R.string.this_is_fine_meme)
            }
            R.id.ultra_fun_meme_2 -> {
                setImage(R.drawable.top_memes_8)
                setText(R.string.such_a_good_meme)
            }
            R.id.ultra_fun_meme_3 -> {
                setImage(R.drawable.top_memes_9)
                setText(R.string.very_good_dog_meme)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setImage(meme: Int) {
        val imgV = findViewById<ImageView>(R.id.img_meme)
        imgV.setImageResource(meme)
    }

    private fun setText(text: Int) {
        val txtV = findViewById<TextView>(R.id.txt_meme)
        txtV.setText(text)
    }
}

