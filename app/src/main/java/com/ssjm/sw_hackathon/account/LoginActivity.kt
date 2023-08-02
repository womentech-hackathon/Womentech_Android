package com.ssjm.sw_hackathon.account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssjm.sw_hackathon.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    // ViewBinding Setting
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}