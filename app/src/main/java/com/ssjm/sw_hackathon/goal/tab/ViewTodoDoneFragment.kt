package com.ssjm.sw_hackathon.goal.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentViewTodoDoneBinding
import com.ssjm.sw_hackathon.goal.tab.recycler.ViewTodoDoneAdapter
import com.ssjm.sw_hackathon.goal.tab.recycler.ViewTodoDoneItem
import com.ssjm.sw_hackathon.goal.tab.recycler.ViewTodoIngAdapter
import com.ssjm.sw_hackathon.goal.tab.recycler.ViewTodoIngItem

class ViewTodoDoneFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentViewTodoDoneBinding? = null
    private val binding get() = _binding!!

    // 실천 리스트 recyclerview adapter
    private var viewTodoDoneItems: MutableList<ViewTodoDoneItem>? = null
    private lateinit var viewTodoDoneAdapter: ViewTodoDoneAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTodoDoneBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        //addTodoDone(ViewTodoDoneItem("인강 듣기", mutableListOf("월", "수", "금"), "2023년 7월 3일", "2023년 7월 18일"))
    }

    private fun initRecycler() {
        viewTodoDoneItems = mutableListOf<ViewTodoDoneItem>()

        // 실천 완료 recyclerview 세팅
        viewTodoDoneAdapter = ViewTodoDoneAdapter(
            requireContext()
        )
        binding.recyclerviewViewTodoDone.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewViewTodoDone.adapter = viewTodoDoneAdapter
        binding.recyclerviewViewTodoDone.isNestedScrollingEnabled = false
        viewTodoDoneAdapter.items = viewTodoDoneItems!!
    }

    private fun addTodoDone(todo: ViewTodoDoneItem) {
        viewTodoDoneItems!!.add(todo)
        viewTodoDoneAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}