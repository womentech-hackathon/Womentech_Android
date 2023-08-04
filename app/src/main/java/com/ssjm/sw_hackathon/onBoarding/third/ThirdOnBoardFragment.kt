package com.ssjm.sw_hackathon.onBoarding.third

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssjm.sw_hackathon.databinding.FragmentThirdOnBoardBinding
import com.ssjm.sw_hackathon.onBoarding.OnBoardingActivity


class ThirdOnBoardFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentThirdOnBoardBinding? = null
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
        _binding = FragmentThirdOnBoardBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}