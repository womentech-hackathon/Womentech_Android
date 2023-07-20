package com.clone.sw_hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clone.sw_hackathon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // ViewBinding Setting
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}