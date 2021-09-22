package com.yyw.myservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

class BoundService : Service() {
    private val _mMyBinder = MyBinder()

    private val _mRandom = Random()

    val randomNumber: Int
        get() = _mRandom.nextInt(100)

    inner class MyBinder : Binder() {
        fun getService(): BoundService = this@BoundService
    }

    override fun onBind(intent: Intent): IBinder = _mMyBinder
}