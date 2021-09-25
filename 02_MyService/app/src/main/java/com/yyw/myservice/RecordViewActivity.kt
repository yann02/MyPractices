package com.yyw.myservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button

class RecordViewActivity : AppCompatActivity() {
    private lateinit var mService: BoundViewService
    private var mBound = false
    private val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(cn: ComponentName, binder: IBinder) {
            mService = (binder as BoundViewService.ViewBinder).getService()
            mBound = true
        }

        override fun onServiceDisconnected(cn: ComponentName) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_view)
        Intent(this, BoundViewService::class.java).apply {
            bindService(this, mConnection, Context.BIND_AUTO_CREATE)
        }
        findViewById<Button>(R.id.btn_show).setOnClickListener {
            mService.showView()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mConnection)
        mBound = false
    }
}