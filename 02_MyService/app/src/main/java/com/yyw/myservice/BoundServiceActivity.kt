package com.yyw.myservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class BoundServiceActivity : AppCompatActivity() {
    private lateinit var mService: BoundService
    private var mBound = false
    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(cn: ComponentName, binder: IBinder) {
            Log.d(TAG, "onServiceConnected")
            mService = (binder as BoundService.MyBinder).getService()
            mBound = true
        }

        override fun onServiceDisconnected(cn: ComponentName) {
            Log.d(TAG, "onServiceDisconnected")
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)
        findViewById<Button>(R.id.btn_get_random).setOnClickListener {
            findViewById<TextView>(R.id.tv_random_text).apply {
                text = try {
                    mService.randomNumber.toString()
                } catch (e: Exception) {
                    e.message
                }
            }
        }
        findViewById<Button>(R.id.btn_bind).setOnClickListener {
            //  绑定服务
            Intent(this, BoundService::class.java).apply {
                bindService(this, mConnection, Context.BIND_AUTO_CREATE)
            }
        }
        findViewById<Button>(R.id.btn_unBind).setOnClickListener {
            //  取消绑定服务
            unbindService(mConnection)
            mBound = false
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()

    }
}