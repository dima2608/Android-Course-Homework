package com.triare.p102quakealertapp.ui.quake

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.triare.p102quakealertapp.R
import com.triare.p102quakealertapp.ui.maps.MapsActivity
import com.triare.p102quakealertapp.ui.quake.fragments.about_us.AboutUsFragment
import com.triare.p102quakealertapp.ui.quake.fragments.home.HomeFragment
import com.triare.p102quakealertapp.ui.quake.fragments.recommendation.RecommendationFragment

class MainActivity : AppCompatActivity() {

    private var toggle: ActionBarDrawerToggle? = null
    private var drawerLayout: DrawerLayout? = null
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
    }

    private fun initUi() {
        initToolbar()
        initMap()
        initDrawer()
        setUpNavView()
        startHomeFragment()
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun initMap(){
        val btnMap = findViewById<ImageView>(R.id.ic_map)
        btnMap.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout?.addDrawerListener(toggle!!)
        toggle?.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpNavView() {
        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.recommendation -> replaceFragment(RecommendationFragment())
                R.id.about_us -> replaceFragment(AboutUsFragment())
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle!!.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
        drawerLayout?.closeDrawers()
    }

    private fun startHomeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, HomeFragment.newInstance())
            .commit()
    }
}

