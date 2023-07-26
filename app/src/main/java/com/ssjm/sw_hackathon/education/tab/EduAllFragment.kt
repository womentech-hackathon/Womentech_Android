package com.ssjm.sw_hackathon.education.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentEduAllBinding

// 교육 > 전체
class EduAllFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentEduAllBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEduAllBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 모두보기 필터 선택
        binding.linearEduFilterAll.setOnClickListener(View.OnClickListener {
            // 모두보기 선택
            binding.linearEduFilterAll.setBackgroundResource(R.drawable.shape_edu_filter_blue)
            binding.textEduFilterAll.setTextColor(requireContext().getColor(R.color.white))

            // 접수중 해제
            binding.linearEduFilterIng.setBackgroundResource(R.drawable.shape_edu_filter_gray)
            binding.textEduFilterIng.setTextColor(requireContext().getColor(R.color.gray05))

            // 마감 해제
            binding.linearEduFilterEnd.setBackgroundResource(R.drawable.shape_edu_filter_gray)
            binding.textEduFilterEnd.setTextColor(requireContext().getColor(R.color.gray05))
        })

        // 접수중 필터 선택
        binding.linearEduFilterIng.setOnClickListener(View.OnClickListener {
            // 모두보기 해제
            binding.linearEduFilterAll.setBackgroundResource(R.drawable.shape_edu_filter_gray)
            binding.textEduFilterAll.setTextColor(requireContext().getColor(R.color.gray05))

            // 접수중 선택
            binding.linearEduFilterIng.setBackgroundResource(R.drawable.shape_edu_filter_blue)
            binding.textEduFilterIng.setTextColor(requireContext().getColor(R.color.white))

            // 마감 해제
            binding.linearEduFilterEnd.setBackgroundResource(R.drawable.shape_edu_filter_gray)
            binding.textEduFilterEnd.setTextColor(requireContext().getColor(R.color.gray05))
        })

        // 마감 필터 선택
        binding.linearEduFilterEnd.setOnClickListener(View.OnClickListener {
            // 모두보기 해제
            binding.linearEduFilterAll.setBackgroundResource(R.drawable.shape_edu_filter_gray)
            binding.textEduFilterAll.setTextColor(requireContext().getColor(R.color.gray05))

            // 접수중 해제
            binding.linearEduFilterIng.setBackgroundResource(R.drawable.shape_edu_filter_gray)
            binding.textEduFilterIng.setTextColor(requireContext().getColor(R.color.gray05))

            // 마감 선택
            binding.linearEduFilterEnd.setBackgroundResource(R.drawable.shape_edu_filter_blue)
            binding.textEduFilterEnd.setTextColor(requireContext().getColor(R.color.white))
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}