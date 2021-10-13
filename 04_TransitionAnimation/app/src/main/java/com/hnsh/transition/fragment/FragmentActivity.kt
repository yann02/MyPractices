package com.hnsh.transition.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hnsh.transition.R

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
    }
}