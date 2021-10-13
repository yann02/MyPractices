package com.hnsh.transition

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialContainerTransform
import com.hnsh.transition.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mbExpandCardView.setOnClickListener {
            showCardView(it)
        }
        binding.mcvCar.setOnClickListener {
            hiddenCardView(it)
        }
    }

    private fun showCardView(v: View) {
        val transform = MaterialContainerTransform().apply {
            startView = v
            endView = binding.mcvCar
            scrimColor = Color.TRANSPARENT
            duration = 5000
            endElevation = resources.getDimension(R.dimen.elevation)
            addTarget(binding.mcvCar)
        }
        TransitionManager.beginDelayedTransition(binding.clMain, transform)
        binding.mcvCar.visibility = View.VISIBLE
        binding.mbExpandCardView.visibility = View.INVISIBLE
    }

    private fun hiddenCardView(v: View) {
        val transform = MaterialContainerTransform().apply {
            startView = v
            endView = binding.mbExpandCardView
            duration = 5000
            scrimColor = Color.TRANSPARENT
            startElevation = resources.getDimension(R.dimen.elevation)
            addTarget(binding.mbExpandCardView)
        }
        TransitionManager.beginDelayedTransition(binding.clMain, transform)
        binding.mcvCar.visibility = View.INVISIBLE
        binding.mbExpandCardView.visibility = View.VISIBLE
    }
}