package com.triare.p102quakealertapp.ui.maps

import android.annotation.SuppressLint
import android.app.Instrumentation
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.triare.p102quakealertapp.R
import com.triare.p102quakealertapp.ui.model.MapsViewModel
import com.triare.p102quakealertapp.utils.BitmapHelper
import kotlinx.android.synthetic.main.item_quake.*
import java.util.jar.Manifest

internal class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val mapsViewModel: MapsViewModel by viewModels()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var btnBack: ImageView? = null
    private var btnGPS: ImageView? = null
    private val includeQuake: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        initMaps()
        initUi()
    }

    private fun initMaps() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    private fun initUi() {
        btnBack = findViewById(R.id.btn_back)
        btnGPS = findViewById(R.id.btn_gps)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isCompassEnabled = false
        addMarkers(mMap)
        btnGPS?.setOnClickListener {
            currentLocation(mMap)
        }
    }

    private fun addMarkers(googleMap: GoogleMap) {
        mapsViewModel.mapsQuakeResultLiveData.observe(this, {
            it.forEach { mapsDvo ->
                val markerIcon: BitmapDescriptor by lazy {
                    BitmapHelper.vectorToBitmap(this, mapsDvo.mapMarker)
                }
                googleMap.addMarker(
                    MarkerOptions()
                        .position(mapsDvo.latLng)
                        .icon(markerIcon)
                )
            }
        })
    }

    @SuppressLint("MissingPermission")
    private fun currentLocation(googleMap: GoogleMap) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val task = fusedLocationProviderClient.lastLocation
        ackPermissions()
        task.addOnSuccessListener {
            val position = LatLng(it.latitude, it.longitude)
            if (it != null ){
                addMarker(position, R.drawable.ic_current_location_marker, googleMap)
                moveCamera(position, googleMap)
            }
        }
    }

    private fun ackPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }
    }

    private fun moveCamera(position: LatLng, googleMap: GoogleMap) {
        googleMap.moveCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition.fromLatLngZoom(
                    position,
                    15f
                )
            )
        )
    }
    private fun addMarker(position: LatLng, icon: Int, googleMap: GoogleMap) {
        val markerIcon: BitmapDescriptor by lazy {
            BitmapHelper.vectorToBitmap(this, icon)
        }

        googleMap.addMarker(
            MarkerOptions()
                .position(position)
                .icon(markerIcon)
        )
    }


}