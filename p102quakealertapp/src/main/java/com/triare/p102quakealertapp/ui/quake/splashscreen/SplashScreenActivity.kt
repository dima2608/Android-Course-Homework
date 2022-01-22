package com.triare.p102quakealertapp.ui.quake.splashscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.triare.p102quakealertapp.ui.quake.MainActivity
import com.triare.p102quakealertapp.R


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        initSplashScreen()
    }

    private fun initSplashScreen() {
        val icBgPattern = findViewById<View>(R.id.ic_bg_pattern_splash_scr)
        icBgPattern.alpha = 0f
        icBgPattern.animate().setDuration(1500).alpha(1f).withEndAction {
            val intentStartMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentStartMainActivity)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}