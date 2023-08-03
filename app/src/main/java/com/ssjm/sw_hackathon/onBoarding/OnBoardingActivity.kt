package com.ssjm.sw_hackathon.onBoarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssjm.sw_hackathon.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    // ViewBinding Setting
    private var _binding: ActivityOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        _binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}