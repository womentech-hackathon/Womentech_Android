package com.ssjm.sw_hackathon.education.bottomsheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentEduOrderBottomBinding


class EduOrderBottomFragment : BottomSheetDialogFragment() {
    // ViewBinding Setting
    private var _binding: FragmentEduOrderBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var eduOrderListener: EduOrderListener

    // 선택된 정렬 기준
    private var orderType = "new"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            eduOrderListener = context as EduOrderListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEduOrderBottomBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 최신순 정렬 선택
        binding.relativeOrderNewBtn.setOnClickListener(View.OnClickListener {
            orderType = "new"

            // 최신순 선택
            binding.imgOrderNewBtnRound.setImageResource(R.drawable.ic_check_round_selected)

            // 오래된순 해제
            binding.imgOrderOldBtnRound.setImageResource(R.drawable.ic_check_round_unselected)

            // 마감순 해체
            binding.imgOrderEndBtnRound.setImageResource(R.drawable.ic_check_round_unselected)
        })

        // 오래된순 정렬 선택
        binding.relativeOrderOldBtn.setOnClickListener(View.OnClickListener {
            orderType = "old"

            // 최신순 해제
            binding.imgOrderNewBtnRound.setImageResource(R.drawable.ic_check_round_unselected)

            // 오래된순 선택
            binding.imgOrderOldBtnRound.setImageResource(R.drawable.ic_check_round_selected)

            // 마감순 해체
            binding.imgOrderEndBtnRound.setImageResource(R.drawable.ic_check_round_unselected)
        })

        // 마감순 정렬 선택
        binding.relativeOrderEndBtn.setOnClickListener(View.OnClickListener {
            orderType = "end"

            // 최신순 해제
            binding.imgOrderNewBtnRound.setImageResource(R.drawable.ic_check_round_unselected)

            // 오래된순 해제
            binding.imgOrderOldBtnRound.setImageResource(R.drawable.ic_check_round_unselected)

            // 마감순 선택
            binding.imgOrderEndBtnRound.setImageResource(R.drawable.ic_check_round_selected)
        })

        // 적용 버튼 선택
        /*binding.linearOrderCompleteBtn.setOnClickListener(View.OnClickListener {
            // 최신순
            if(orderType == "new") {
                eduOrderListener.orderNew()
            }

            // 오래된순
            if(orderType == "old") {
                eduOrderListener.orderOld()
            }

            // 마감순
            if(orderType == "end") {
                eduOrderListener.orderEnd()
            }

            dismiss()
        })*/

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}