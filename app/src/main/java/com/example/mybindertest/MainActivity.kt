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

//    onCreate的bundle可能为空
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

//   在后台别kill后，旋转屏幕方向等在activity销毁后重建时会调用。第一次创建activity时不会调用
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.i("zpppppppp", "onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }

//    退后台 切换到其他activity 按下电源键 从任务栈切换到其他进程时会调用 在onStop后调用
    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("zpppppppp", "onSaveInstanceState onSaveInstanceState onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        Log.i("zpppppppp", "onStop onStop onStop")
        super.onStop()
    }

    override fun onPause() {
        Log.i("zpppppppp", "onPause onPause onPause")
        super.onPause()
    }
}