package com.ssjm.sw_hackathon.onBoarding.second

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentSecondNewOnBoardBinding
import com.ssjm.sw_hackathon.onBoarding.OnBoardingActivity
import com.ssjm.sw_hackathon.onBoarding.third.ThirdOnBoardFragment

// 온보딩 Step2 > 새로운 직업
class SecondNewOnBoardFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentSecondNewOnBoardBinding? = null
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
        _binding = FragmentSecondNewOnBoardBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 이전으로
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            requireActivity().supportFragmentManager.popBackStack()
        }

        // 예시보기
        binding.linearHintBtn.setOnClickListener(View.OnClickListener {
            binding.relativeToolTip.visibility = View.VISIBLE
        })

        // 예시보기 닫기
        binding.btnCloseToolTip.setOnClickListener {
            binding.relativeToolTip.visibility = View.GONE
        }

        // 다음으로
        binding.linearNextOnboard.setOnClickListener(View.OnClickListener {
            // 입력하지 않은 경우
            var job: String? = binding.editOnboardSecond.text.toString()
            if(job == null || job == "") {
                Toast.makeText(requireContext(), getString(R.string.toast_unselect_second_job), Toast.LENGTH_SHORT).show()
            }
            // 입력한 경우
            else {
                //activity?.setFragment(ThirdOnBoardFragment())
                val bundle = Bundle()
                bundle.putString("goal", job)
                activity?.moveWithBundleFragment(ThirdOnBoardFragment(), bundle)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}