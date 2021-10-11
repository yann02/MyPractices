package com.yyw.myservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

class BoundService : Service() {
    private val _mMyBinder = MyBinder()

    private val _mRandom = Random()

    val randomNumber: Int
        get() = _mRandom.nextInt(100)

    inner class MyBinder : Binder() {
        fun getService(): BoundService = this@BoundService
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind")
        return _mMyBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind")
    }
}