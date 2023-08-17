package com.ssjm.sw_hackathon.goal.viewTodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ssjm.sw_hackathon.databinding.FragmentViewTodoContentBinding
import com.ssjm.sw_hackathon.education.tab.EduViewPagerAdapter
import com.ssjm.sw_hackathon.goal.tab.ViewTodoDoneFragment
import com.ssjm.sw_hackathon.goal.tab.ViewTodoIngFragment


class ViewTodoContentFragment : Fragment() {

    // ViewBinding Setting
    private var _binding: FragmentViewTodoContentBinding? = null
    private val binding get() = _binding!!

    // viewPager
    lateinit var viewPagers: ViewPager
    lateinit var tabLayouts: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTodoContentBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager(2, 0)

        // 이전으로
        binding.btnBack.setOnClickListener {
            view.findNavController().popBackStack()
        }
    }

    private fun initViewPager(ingCount: Int, doneCount: Int) {
        viewPagers = binding.viewpagerViewTodo
        tabLayouts = binding.tablayoutViewTodo

        // 실천사항 탭에 보여줄 Fragment
        val viewTodoIngFragment = ViewTodoIngFragment()
        val viewTodoDoneFragment = ViewTodoDoneFragment()

        // 실천 중 / 실천 종료 title
        val ingTab = "실천중 " + ingCount
        val doneTab = "실천 종료 " + doneCount

        // 실천사항 탭에 각 Fragment 배치
        val adapter = EduViewPagerAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(viewTodoIngFragment, ingTab)
        adapter.addFragment(viewTodoDoneFragment, doneTab)

        viewPagers.adapter = adapter
        tabLayouts.setupWithViewPager(viewPagers)

        // Tab 전환
        tabLayouts.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}