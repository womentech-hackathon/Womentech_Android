package com.ssjm.sw_hackathon.education

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssjm.sw_hackathon.databinding.FragmentEducationBinding
import com.ssjm.sw_hackathon.educationApi.EducationRow
import com.ssjm.sw_hackathon.educationApi.apiGetEducationInfo


// 교육 탭
class EducationFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentEducationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEducationBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 서울시 어르신 취업지원센터 교육정보 조회
        apiGetEducationInfo(
            1,
            2,
            addEducationList = {
                addEducationList(it)
            }
        )
    }

    fun addEducationList(educationDatas: MutableList<EducationRow>) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}