package com.ssjm.sw_hackathon.goal.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentViewTodoIngBinding
import com.ssjm.sw_hackathon.goal.tab.recycler.ViewTodoIngAdapter
import com.ssjm.sw_hackathon.goal.tab.recycler.ViewTodoIngItem
import com.ssjm.sw_hackathon.home.recycler.HomeTodoAdapter
import com.ssjm.sw_hackathon.home.recycler.HomeTodoItem


class ViewTodoIngFragment : Fragment() {

    // ViewBinding Setting
    private var _binding: FragmentViewTodoIngBinding? = null
    private val binding get() = _binding!!

    // 실천 리스트 recyclerview adapter
    private var viewTodoIngItems: MutableList<ViewTodoIngItem>? = null
    private lateinit var viewTodoIngAdapter: ViewTodoIngAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTodoIngBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        addTodoIng(ViewTodoIngItem("라떼 아트", mutableListOf("월", "토", "일"), "2023년 8월 6일"))
        addTodoIng(ViewTodoIngItem("오전 10:00 실기학원", mutableListOf("토", "일"), "2023년 8월 6일"))
    }

    private fun initRecycler() {
        viewTodoIngItems = mutableListOf<ViewTodoIngItem>()

        // 실천 중 recyclerview 세팅
        viewTodoIngAdapter = ViewTodoIngAdapter(
            requireContext()
        )
        binding.recyclerviewViewTodoIng.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewViewTodoIng.adapter = viewTodoIngAdapter
        binding.recyclerviewViewTodoIng.isNestedScrollingEnabled = false
        viewTodoIngAdapter.items = viewTodoIngItems!!
    }

    private fun addTodoIng(todo: ViewTodoIngItem) {
        viewTodoIngItems!!.add(todo)
        viewTodoIngAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}