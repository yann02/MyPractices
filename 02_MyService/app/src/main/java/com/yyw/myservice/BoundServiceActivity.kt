package com.yyw.myservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BoundServiceActivity : AppCompatActivity() {
    private lateinit var mService: BoundService
    private var mBound = false
    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(cn: ComponentName, binder: IBinder) {
            mService = (binder as BoundService.MyBinder).getService()
            mBound = true
        }

        override fun onServiceDisconnected(cn: ComponentName) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)
        findViewById<Button>(R.id.btn_get_random).setOnClickListener {
            findViewById<TextView>(R.id.tv_random_text).apply {
                text = mService.randomNumber.toString()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, BoundService::class.java).apply {
            bindService(this, mConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(mConnection)
        mBound = false
    }
}