package com.triare.p062constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_manu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_btn_previous_layout -> {
                setContentView(R.layout.activity_main)
                Toast.makeText(applicationContext, "First layout", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_btn_next_layout -> {
                setContentView(R.layout.andr_std)
                Toast.makeText(applicationContext, "Second layout", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}