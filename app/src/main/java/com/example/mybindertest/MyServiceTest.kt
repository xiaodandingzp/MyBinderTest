package com.example.mybindertest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyServiceTest : Service() {

    private val mBinder = object : IMyAidlTest.Stub() {

        override fun test() {
            Log.i("zppppppp", "test test test")
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

}