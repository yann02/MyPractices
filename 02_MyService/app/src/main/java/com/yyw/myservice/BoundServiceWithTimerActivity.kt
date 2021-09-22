package com.yyw.myservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.telecom.ConnectionService
import android.widget.Button

class BoundServiceWithTimerActivity : AppCompatActivity() {
    private lateinit var mService: BoundTimerService
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(cn: ComponentName, binder: IBinder) {
            mService = (binder as BoundTimerService.TimerBinder).getService()
        }

        override fun onServiceDisconnected(cn: ComponentName?) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service_with_timer)
        findViewById<Button>(R.id.btn_start).setOnClickListener {
            mService.onStart()
        }
        findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            mService.onCancel()
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, BoundTimerService::class.java).apply {
            bindService(this, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(serviceConnection)
    }
}