package com.hnsh.transition.fragment.container

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.hnsh.transition.R
import com.hnsh.transition.databinding.ActivityContainerBinding
import com.hnsh.transition.fragment.EndFragment
import com.hnsh.transition.fragment.StartFragment

class ContainerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContainerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater).apply {
            setContentView(root)
            btnForward.setOnClickListener {
                commitFragment(EndFragment())
            }
            btnBackward.setOnClickListener {
                commitFragment(StartFragment())
            }
        }
    }

    private fun commitFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_container, fragment).commit()
    }
}