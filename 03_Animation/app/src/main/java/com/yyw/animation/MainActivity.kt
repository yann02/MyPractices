package com.yyw.animation

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<MaterialButton>(R.id.mb_destination).setOnClickListener {
            Intent(this, DestinationActivity::class.java).apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(this, ActivityOptions.makeSceneTransitionAnimation(this@MainActivity).toBundle())
                } else {
                    startActivity(this)
                }
            }
        }
    }
}