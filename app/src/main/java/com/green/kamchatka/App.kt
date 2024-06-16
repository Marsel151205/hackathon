package com.green.kamchatka

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        MapKitFactory.setApiKey("c359b53e-de28-43ba-8ed2-8a930c09448c")
        super.onCreate()
    }
}