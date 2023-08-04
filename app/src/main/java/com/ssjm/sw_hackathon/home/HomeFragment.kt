package com.ssjm.sw_hackathon.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssjm.sw_hackathon.databinding.FragmentHomeBinding
import com.ssjm.sw_hackathon.home.recycler.HomeTodoAdapter
import com.ssjm.sw_hackathon.home.recycler.HomeTodoItem

// 메인 탭
class HomeFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // 실천 리스트 recyclerview adapter
    private var homeTodoItems: MutableList<HomeTodoItem>? = null
    private lateinit var homeTodoAdapter: HomeTodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        addTodo(HomeTodoItem("note1", "노트에 필사하기", "66일째 실천중", mutableListOf("월", "수")))
        addTodo(HomeTodoItem("barista2", "실기 학원", "32일째 실천중", mutableListOf("화", "목")))
    }

    // recyclerview 세팅
    private fun initRecycler() {
        homeTodoItems = mutableListOf<HomeTodoItem>()

        // 링크 리스트 recyclerview 세팅
        homeTodoAdapter = HomeTodoAdapter(
            requireContext()
        )
        binding.recyclerviewHomeTodo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewHomeTodo.adapter = homeTodoAdapter
        binding.recyclerviewHomeTodo.isNestedScrollingEnabled = true
        homeTodoAdapter.items = homeTodoItems!!
    }

    private fun addTodo(todo: HomeTodoItem) {
        homeTodoItems!!.add(todo)
        homeTodoAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}