package com.yyw.myservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

const val TAG = "wyy"
const val PERIOD = 5 * 1000L

class BoundTimerService : Service() {
    private val mTimerBinder = TimerBinder()
    private var mTimer: Timer? = null
    private var mTimerTask: TimerTask? = null
    private var counter = 0

    fun onStart() {
        onCancel()
        initTimer()
        mTimer?.schedule(mTimerTask, 0, PERIOD)
    }

    private fun initTimer() {
        mTimer = Timer()
        mTimerTask = object : TimerTask() {
            override fun run() {
                Log.d(TAG, "Thread name:${Thread.currentThread().name}, counter:${counter++}")
            }
        }
    }

    fun onCancel() {
        cancelTimer()
    }

    private fun cancelTimer() {
        mTimer?.cancel()
        mTimer = null
        mTimerTask?.cancel()
        mTimerTask = null
    }

    inner class TimerBinder : Binder() {
        fun getService(): BoundTimerService = this@BoundTimerService
    }

    override fun onBind(it: Intent): IBinder = mTimerBinder

    override fun onUnbind(intent: Intent): Boolean {
        cancelTimer()
        return super.onUnbind(intent)
    }
}