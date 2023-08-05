package com.ssjm.sw_hackathon.token

import android.app.Application

class GloabalApplication: Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }
    override fun onCreate() {
        super.onCreate()

        prefs = PreferenceUtil(applicationContext)
    }
}