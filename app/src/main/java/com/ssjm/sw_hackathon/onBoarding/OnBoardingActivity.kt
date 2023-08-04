package com.ssjm.sw_hackathon.onBoarding

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ssjm.sw_hackathon.MainActivity
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.ActivityOnBoardingBinding
import com.ssjm.sw_hackathon.onBoarding.first.FirstOnBoardFragment

class OnBoardingActivity : AppCompatActivity() {
    // ViewBinding Setting
    private var _binding: ActivityOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        _binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Step 1
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentOnBoarding.id, FirstOnBoardFragment())
            .commit()
    }

    fun setFragment(frag : Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentOnBoarding.id, frag)
            .commit()
    }

    // 선택 안 하고 홈으로 이동
    fun unselectAndGoHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private var doubleBackToExit = false
    override fun onBackPressed() {
        if (doubleBackToExit) {
            finishAffinity()
        } else {
            // 현재 액티비티
            val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val info = manager.getRunningTasks(1)
            val componentName = info[0].topActivity
            val ActivityName = componentName!!.shortClassName.substring(1)

            // 온보딩 액티비티인 경우
            if(ActivityName == "onBoarding.OnBoardingActivity") {
                var currentFragment: Fragment? = null
                var cntFragment: Int = 0

                // 현재 프래그먼트 찾기
                for (fragment: Fragment in supportFragmentManager.fragments) {
                    if (fragment.isVisible) {
                        currentFragment = fragment
                        cntFragment++
                    }
                }

                // 현재 프래그먼트가 첫번째 단계가 아닌 경우
                if (cntFragment > 1) {
                    // 이전 페이지로 이동
                    supportFragmentManager.beginTransaction().remove(currentFragment!!).commit()
                    supportFragmentManager.popBackStack()
                }
                // 현재 프래그먼트가 첫번째 단계인 경우
                else {
                    Toast.makeText(this, getString(R.string.toast_back_main_page), Toast.LENGTH_SHORT).show()
                    doubleBackToExit = true
                    runDelayed(1500L) {
                        doubleBackToExit = false
                    }
                }
            }
        }
    }

    fun runDelayed(millis: Long, function: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(function, millis)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}