package com.ssjm.sw_hackathon.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.ssjm.sw_hackathon.MainActivity
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.accountApi.apiLogin
import com.ssjm.sw_hackathon.accountApi.apiSignUp
import com.ssjm.sw_hackathon.accountApi.login.LoginRequest
import com.ssjm.sw_hackathon.accountApi.login.LoginResult
import com.ssjm.sw_hackathon.accountApi.signUp.SignUpRequest
import com.ssjm.sw_hackathon.databinding.ActivitySignUpBinding
import com.ssjm.sw_hackathon.token.GloabalApplication

class SignUpActivity : AppCompatActivity() {
    // ViewBinding Setting
    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!

    private var name: String? = null
    private var id: String? = null
    private var password: String? = null
    private var rePassword: String? = null

    private var allInput = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이름 입력
        binding.editSignUpName.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                name = binding.editSignUpName.text.toString()
                checkAllInput()
            }
        })

        // 아이디 입력
        binding.editSignUpId.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                id = binding.editSignUpId.text.toString()
                checkAllInput()
            }
        })

        // 비밀번호 입력
        binding.editSignUpPassword.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                password = binding.editSignUpPassword.text.toString()
                checkAllInput()
            }
        })

        // 비밀번호 재확인 입력
        binding.editSignUpPasswordRe.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                rePassword = binding.editSignUpPasswordRe.text.toString()
                checkAllInput()
            }
        })

        // 회원가입
        binding.linearSignUpDone.setOnClickListener(View.OnClickListener {
            if(allInput) {
                apiSignUp(
                    SignUpRequest(id!!, name!!, password!!),
                    checkComplete = {
                        checkComplete(it)
                    }
                )
            }
        })
    }

    private fun checkAllInput() {
        allInput = false
        if(name != null && name != "") {
            if(id != null && id != "") {
                if(password != null && password != "" && password == rePassword) {
                    allInput = true
                    binding.linearSignUpDone.setBackgroundColor(resources.getColor(R.color.main_color_1))
                }
            }
        }
    }

    // 회원가입 성공
    private fun checkComplete(isComplete: Boolean) {
        if(isComplete) {
            apiLogin(
                LoginRequest(id!!, password!!),
                checkComplete = {
                    checkLogin(it)
                }
            )
        }
    }

    // 로그인 성공
    private fun checkLogin(token: LoginResult) {
        // 토큰값 저장
        var accessToken = token.accessToken.toString()
        var refreshToken = token.accessToken.toString()

        GloabalApplication.prefs.setString("accessToken", accessToken)
        GloabalApplication.prefs.setString("refreshToken", refreshToken)
        GloabalApplication.prefs.setString("name", name!!)

        val intent = Intent(this, DoneSignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}