package com.ssjm.sw_hackathon.onBoarding.first

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentFirstOnBoardBinding
import com.ssjm.sw_hackathon.onBoarding.OnBoardingActivity
import com.ssjm.sw_hackathon.onBoarding.second.SecondKeepOnBoardFragment
import com.ssjm.sw_hackathon.onBoarding.second.SecondNewOnBoardFragment
import com.ssjm.sw_hackathon.onBoarding.second.SecondYetOnBoardFragment


// 온보딩 첫 페이지
class FirstOnBoardFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentFirstOnBoardBinding? = null
    private val binding get() = _binding!!

    // 프래그먼트 전환을 위해
    var activity: OnBoardingActivity? = null

    var selected = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = getActivity() as OnBoardingActivity

        if(selected == 1) {
            binding.linearFirstSelect1.setBackgroundResource(R.drawable.shape_onboard_step1_btn_select)
            binding.textFirstSelect1.setTextColor(resources.getColor(R.color.white))

            binding.linearFirstSelect2.setBackgroundResource(R.drawable.shape_onboard_step1_btn)
            binding.textFirstSelect2.setTextColor(resources.getColor(R.color.black02))

            binding.linearFirstSelect3.setBackgroundResource(R.drawable.shape_onboard_step1_btn)
            binding.textFirstSelect3.setTextColor(resources.getColor(R.color.black02))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstOnBoardBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 새로운 직업을 갖고 싶어요
        binding.linearFirstSelect1.setOnClickListener(View.OnClickListener {
            selected = 1

            binding.linearFirstSelect1.setBackgroundResource(R.drawable.shape_onboard_step1_btn_select)
            binding.textFirstSelect1.setTextColor(resources.getColor(R.color.white))

            binding.linearFirstSelect2.setBackgroundResource(R.drawable.shape_onboard_step1_btn)
            binding.textFirstSelect2.setTextColor(resources.getColor(R.color.black02))

            binding.linearFirstSelect3.setBackgroundResource(R.drawable.shape_onboard_step1_btn)
            binding.textFirstSelect3.setTextColor(resources.getColor(R.color.black02))
        })

        // 이전 경력을 살리고 싶어요
        binding.linearFirstSelect2.setOnClickListener(View.OnClickListener {
            selected = 2

            binding.linearFirstSelect1.setBackgroundResource(R.drawable.shape_onboard_step1_btn)
            binding.textFirstSelect1.setTextColor(resources.getColor(R.color.black02))

            binding.linearFirstSelect2.setBackgroundResource(R.drawable.shape_onboard_step1_btn_select)
            binding.textFirstSelect2.setTextColor(resources.getColor(R.color.white))

            binding.linearFirstSelect3.setBackgroundResource(R.drawable.shape_onboard_step1_btn)
            binding.textFirstSelect3.setTextColor(resources.getColor(R.color.black02))
        })

        // 잘 모르겠어요
        binding.linearFirstSelect3.setOnClickListener(View.OnClickListener {
            selected = 3

            binding.linearFirstSelect1.setBackgroundResource(R.drawable.shape_onboard_step1_btn)
            binding.textFirstSelect1.setTextColor(resources.getColor(R.color.black02))

            binding.linearFirstSelect2.setBackgroundResource(R.drawable.shape_onboard_step1_btn)
            binding.textFirstSelect2.setTextColor(resources.getColor(R.color.black02))

            binding.linearFirstSelect3.setBackgroundResource(R.drawable.shape_onboard_step1_btn_select)
            binding.textFirstSelect3.setTextColor(resources.getColor(R.color.white))
        })

        // 선택 안 하고 홈으로 이동할래요
        binding.linearNotSelect.setOnClickListener(View.OnClickListener {
            activity?.unselectAndGoHome()
        })

        // 다음으로
        binding.linearNextOnboard.setOnClickListener(View.OnClickListener {
            // 선택하지 않은 경우
            if(selected == 0) {
                Toast.makeText(requireContext(), getString(R.string.toast_unselect_first), Toast.LENGTH_SHORT).show()
            }
            // 새로운 직업을 갖고 싶어요
            else if(selected == 1) {
                activity?.setFragment(SecondNewOnBoardFragment())
            }
            // 이전 경력을 살리고 싶어요
            else if(selected == 2) {
                activity?.setFragment(SecondKeepOnBoardFragment())
            }
            // 잘 모르겠어요
            else if(selected == 3) {
                activity?.setFragment(SecondYetOnBoardFragment())
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}