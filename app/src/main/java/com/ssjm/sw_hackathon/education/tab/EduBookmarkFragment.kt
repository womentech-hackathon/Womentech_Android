package com.ssjm.sw_hackathon.education.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ssjm.sw_hackathon.databinding.FragmentEduBookmarkBinding

// 교육 > 찜
class EduBookmarkFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentEduBookmarkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEduBookmarkBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}