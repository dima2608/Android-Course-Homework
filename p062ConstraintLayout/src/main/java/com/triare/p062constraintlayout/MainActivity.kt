package com.triare.p062constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nextLayout = findViewById<ImageButton>(R.id.similar)

        nextLayout.setOnClickListener {
            setContentView(R.layout.andr_std)
        }
    }
}