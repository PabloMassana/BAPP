package com.falconteam.bapp

import android.app.Application
import com.falconteam.bapp.di.appModule
import org.koin.core.context.startKoin

class BaapApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }

}