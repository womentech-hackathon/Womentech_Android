package com.ssjm.sw_hackathon.onBoarding.second

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentSecondYetOnBoardBinding
import com.ssjm.sw_hackathon.onBoarding.OnBoardingActivity


// 온보딩 Step2 > 잘 모르겠어요, 다양한 직무 교육을 수강해보고 싶어요.
class SecondYetOnBoardFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentSecondYetOnBoardBinding? = null
    private val binding get() = _binding!!

    // 프래그먼트 전환을 위해
    var activity: OnBoardingActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = getActivity() as OnBoardingActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondYetOnBoardBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 이전으로
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            requireActivity().supportFragmentManager.popBackStack()
        }

        // 교육 정보 보기
        binding.linearViewEduOnboard.setOnClickListener(View.OnClickListener {
            activity?.viewEdu()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}