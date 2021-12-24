package com.triare.p102quakealertapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.google.android.material.navigation.NavigationView
import com.triare.p102quakealertapp.fragments.AboutUsFragment
import com.triare.p102quakealertapp.fragments.HomeFragment
import com.triare.p102quakealertapp.fragments.RecommendationFragment

class MainActivity : AppCompatActivity() {

    private var toggle: ActionBarDrawerToggle? = null
    private var drawerLayout: DrawerLayout? = null
    private var appBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()

    }

    private fun initUi() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout?.addDrawerListener(toggle!!)
        toggle?.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpNavView()
        startHomeFragment()
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration!!)
                || super.onSupportNavigateUp()
    }

}

