package com.ssjm.sw_hackathon.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ssjm.sw_hackathon.databinding.ActivityDoneSignUpBinding
import com.ssjm.sw_hackathon.onBoarding.OnBoardingActivity

class DoneSignUpActivity : AppCompatActivity() {
    // ViewBinding Setting
    private var _binding: ActivityDoneSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        _binding = ActivityDoneSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.linearStartBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}