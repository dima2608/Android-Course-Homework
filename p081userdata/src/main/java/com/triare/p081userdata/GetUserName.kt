package com.triare.p081userdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class GetUserName : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_user_name)

        val userName = findViewById<TextInputLayout>(R.id.get_user_name).editText?.text
        val btnSave = findViewById<TextView>(R.id.btn_save_user_name)
        val btnGoBack = findViewById<ImageButton>(R.id.btn_go_back_user_name)

        btnGoBack.setOnClickListener {
            finish()
        }

        btnSave.setOnClickListener {
            if (userName.toString().isEmpty()) {
                Toast.makeText(this, "You did not enter your name ", Toast.LENGTH_SHORT).show()
            }else {
                setResult(REQUEST_USER_NAME, Intent().putExtra("UserName", userName.toString()))
                finish()
            }
        }

    }
    companion object  {
        const val REQUEST_USER_NAME = 1
    }

}