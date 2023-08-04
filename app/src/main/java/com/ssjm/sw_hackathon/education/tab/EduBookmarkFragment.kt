package com.ssjm.sw_hackathon.education.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.ssjm.sw_hackathon.databinding.FragmentEduBookmarkBinding
import com.ssjm.sw_hackathon.education.recycler.EducationAdapter
import com.ssjm.sw_hackathon.education.recycler.EducationItemInterface

// 교육 > 찜
class EduBookmarkFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentEduBookmarkBinding? = null
    private val binding get() = _binding!!

    // 전체
    private var bookmarkEducationItems: MutableList<EducationItemInterface>? = null

    // RecyclerView Adapter
    private lateinit var educationAdapter: EducationAdapter

    // 현재 페이지
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEduBookmarkBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerview 세팅
        initRecycler()
    }

    // 교육 아이템 recyclerview 세팅
    private fun initRecycler() {
        bookmarkEducationItems = mutableListOf<EducationItemInterface>()

        educationAdapter = EducationAdapter(requireContext())
        binding.recyclerviewEduBookmark.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewEduBookmark.adapter = educationAdapter
        binding.recyclerviewEduBookmark.isNestedScrollingEnabled = false // 스크롤을 매끄럽게 해줌

        educationAdapter.items = bookmarkEducationItems!!
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}