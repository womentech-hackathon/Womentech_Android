package com.ssjm.sw_hackathon.goal.addTodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentAddTodoContentBinding
import com.ssjm.sw_hackathon.goal.addTodo.recycler.AddTodoAdapter
import com.ssjm.sw_hackathon.goal.addTodo.recycler.AddTodoItem
import com.ssjm.sw_hackathon.goal.recycler.DayOfWeekAdapter
import com.ssjm.sw_hackathon.goal.recycler.DayOfWeekItem

class AddTodoContentFragment : Fragment() {

    // ViewBinding Setting
    private var _binding: FragmentAddTodoContentBinding? = null
    private val binding get() = _binding!!

    // 실천 날짜 리스트 recyclerview adapter
    private var addTodoItems: MutableList<AddTodoItem>? = null
    private lateinit var addTodoAdapter: AddTodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTodoContentBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        // 이전으로
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            requireActivity().supportFragmentManager.popBackStack()
        }

        addTodo(AddTodoItem())

        // 추가 버튼
        binding.linearAddTodoBtn.setOnClickListener(View.OnClickListener {
            addTodo(AddTodoItem())
        })
    }
    private fun initRecycler() {
        addTodoItems = mutableListOf<AddTodoItem>()

        // 날짜 리스트 recyclerview 세팅
        addTodoAdapter = AddTodoAdapter(
            requireContext()
        )
        binding.recyclerviewAddTodo.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewAddTodo.adapter = addTodoAdapter
        binding.recyclerviewAddTodo.isNestedScrollingEnabled = false
        addTodoAdapter.items = addTodoItems!!
    }

    private fun addTodo(todo: AddTodoItem) {
        addTodoItems!!.add(todo)
        addTodoAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}