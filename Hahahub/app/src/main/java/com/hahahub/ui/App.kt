package com.hahahub.ui

import android.app.Application
import com.hahahub.data.db.JokeDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //JokeDatabase.initDatabase(this)
    }
}
