package com.yyw.myservice

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager

class BoundViewService : Service() {

    private val binder = ViewBinder()
    private lateinit var mWM: WindowManager
    private var rootView: View? = null

    inner class ViewBinder : Binder() {
        fun getService(): BoundViewService = this@BoundViewService
    }

    override fun onBind(intent: Intent): IBinder {
        mWM = applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return binder
    }

    fun showView() {
        mWM.addView(getRecordView(), getShadowLayoutParams())
    }

    private fun getRecordView(): View? {
        rootView = LayoutInflater.from(applicationContext).inflate(R.layout.recording_layout, null)
        rootView?.setOnClickListener {
            hiddenView()
        }
        val recordView = rootView?.findViewById<RecordView>(R.id.rv_command)
        recordView?.setLoadingState(true)
        recordView?.isEnabled = false
        recordView?.startAnim()
        return rootView
    }

    private fun hiddenView() {
        if (null == rootView) {
            return
        }
        mWM.removeView(rootView)
    }

    private fun getShadowLayoutParams(): WindowManager.LayoutParams {
        return WindowManager.LayoutParams(
            WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        ).apply {
            format = PixelFormat.TRANSPARENT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            }
            height = WindowManager.LayoutParams.MATCH_PARENT
            gravity = Gravity.BOTTOM
        }
    }
}