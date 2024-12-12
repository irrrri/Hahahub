package com.hahahub.startup

import android.app.Application
import com.hahahub.di.AppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
    }
}

