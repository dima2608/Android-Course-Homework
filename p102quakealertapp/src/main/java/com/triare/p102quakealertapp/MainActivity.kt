package com.triare.p102quakealertapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.triare.p102quakealertapp.fragments.*

class MainActivity : AppCompatActivity() {

    private val dataQuakeModel: QuakeViewModel by viewModels()

    private var toggle: ActionBarDrawerToggle? = null
    private var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startHomeFragment()

    }

    private fun startHomeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, HomeFragment.newInstance())
            .commit()
    }


}