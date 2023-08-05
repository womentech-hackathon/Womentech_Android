package com.ssjm.sw_hackathon.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ssjm.sw_hackathon.MainActivity
import com.ssjm.sw_hackathon.accountApi.apiLogin
import com.ssjm.sw_hackathon.accountApi.login.LoginRequest
import com.ssjm.sw_hackathon.accountApi.login.LoginResult
import com.ssjm.sw_hackathon.databinding.ActivityLoginBinding
import com.ssjm.sw_hackathon.token.GloabalApplication

class LoginActivity : AppCompatActivity() {
    // ViewBinding Setting
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이전에 로그인 했는지 확인 -> 이미 로그인 되어있다면 바로 메인 페이지로 이동
        var accessToken: String = GloabalApplication.prefs.getString("accessToken", "")
        var refreshToken: String = GloabalApplication.prefs.getString("refreshToken", "")

        if(accessToken.length > 0 && refreshToken.length > 0) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 로그인 -> 메인 페이지로
        binding.textLoginBtn.setOnClickListener(View.OnClickListener {
            val id = binding.editLoginId.text.toString()
            val password = binding.editLoginPassword.text.toString()

            if(id.length > 0 && password.length > 0) {
                apiLogin(
                    LoginRequest(id, password),
                    checkComplete = {
                        checkLogin(it)
                    }
                )
            }
        })

        // 회원가입 페이지로
        binding.textSignUpBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    // 로그인 성공
    private fun checkLogin(token: LoginResult) {

        // 토큰값 저장
        var accessToken = token.accessToken.toString()
        var refreshToken = token.accessToken.toString()

        GloabalApplication.prefs.setString("accessToken", accessToken)
        GloabalApplication.prefs.setString("refreshToken", refreshToken)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}