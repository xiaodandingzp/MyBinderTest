package com.example.mybindertest

import android.app.Application
import android.content.Context
import android.util.Log



class BeautyCallApp : Application() {
    private var startTime = 0L
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "#onCreate")
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        Log.i(TAG, "end attachBaseContext")
    }

    companion object {
        private const val TAG = "BeautyCallApp"
    }
}