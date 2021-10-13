package com.hnsh.transition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hnsh.transition.databinding.ActivityMainBinding
import com.hnsh.transition.fragment.FragmentActivity
import com.hnsh.transition.fragment.container.ContainerActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            mbView.setOnClickListener {
                startActivityWithTargetActivity(ViewActivity::class.java)
            }
            mbFragment.setOnClickListener {
                startActivityWithTargetActivity(FragmentActivity::class.java)
            }
            mbContainer.setOnClickListener {
                startActivityWithTargetActivity(ContainerActivity::class.java)
            }
            setContentView(root)
        }
    }

    private fun startActivityWithTargetActivity(clazz: Class<out AppCompatActivity>) {
        Intent(this, clazz).apply {
            startActivity(this)
        }
    }
}