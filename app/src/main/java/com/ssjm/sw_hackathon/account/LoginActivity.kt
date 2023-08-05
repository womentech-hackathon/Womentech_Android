package com.ssjm.sw_hackathon.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ssjm.sw_hackathon.MainActivity
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

        // 로그인 -> 메인 페이지로
        binding.textLoginBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

        // 회원가입 페이지로
        binding.textSignUpBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}