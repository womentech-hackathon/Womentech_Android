package com.ssjm.sw_hackathon.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.ssjm.sw_hackathon.MainActivity
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.ActivitySignUpBinding

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
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}