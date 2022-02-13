package com.triare.p102quakealertapp

import android.app.Application
import com.google.android.gms.maps.MapsInitializer

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        MapsInitializer.initialize(applicationContext, MapsInitializer.Renderer.LATEST, null)
    }
}