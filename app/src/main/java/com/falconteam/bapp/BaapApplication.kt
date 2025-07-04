package com.falconteam.bapp

import android.app.Application
import com.falconteam.bapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaapApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaapApplication)
            modules(appModule)
        }
    }
}