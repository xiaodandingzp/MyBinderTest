package com.example.mybindertest

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.core.content.getSystemService
import java.util.concurrent.Executors


class MainActivity : ComponentActivity() {

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i("zpppppp", "binder content");
            val myBinderTest = IMyAidlTest.Stub.asInterface(service)
            myBinderTest?.let {
                it.test()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i("zpppppp", "binder died")
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.application.getSystemService<ActivityManager>()
        setContentView(R.layout.mian_activity)
        val intent = Intent(this, MyServiceTest::class.java)
        //绑定服务，后面会回调onServiceConnected方法
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        Executors.newSingleThreadExecutor()
        Log.i("zpppppp", "Executors: ")
        findViewById<Button>(R.id.button).setOnClickListener {
            val uri_user = Uri.parse("content://zp.com.ping")
            contentResolver.insert(uri_user, null)
        }
    }
}