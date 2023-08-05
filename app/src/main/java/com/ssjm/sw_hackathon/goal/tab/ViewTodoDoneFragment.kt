package com.ssjm.sw_hackathon.goal.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentViewTodoDoneBinding


class ViewTodoDoneFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentViewTodoDoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTodoDoneBinding.inflate(layoutInflater)

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