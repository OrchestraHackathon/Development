package com.example.jonggangtime

import android.app.Application
import com.example.jonggangtime.Utils.PreferenceUtil

class MyApplication: Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}