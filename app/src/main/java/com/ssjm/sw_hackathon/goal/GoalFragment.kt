package com.ssjm.sw_hackathon.goal

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentGoalBinding
import com.ssjm.sw_hackathon.goal.recycler.DayOfWeekAdapter
import com.ssjm.sw_hackathon.goal.recycler.DayOfWeekItem
import com.ssjm.sw_hackathon.goal.recycler.TodoOfDayAdapter
import com.ssjm.sw_hackathon.goal.recycler.TodoOfDayItem
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

public var checkAdd = false

// 목표 탭
class GoalFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentGoalBinding? = null
    private val binding get() = _binding!!

    // 실천 날짜 리스트 recyclerview adapter
    private var dayOfWeekItems: MutableList<DayOfWeekItem>? = null
    private lateinit var dayOfWeekAdapter: DayOfWeekAdapter

    // 실천 내용 리스트 recyclerview adapter
    private var todoOfDayItems: MutableList<TodoOfDayItem>? = null
    private lateinit var todoOfDayAdapter: TodoOfDayAdapter

    private lateinit var today: LocalDate
    private lateinit var startDay: LocalDate
    var progressValues = mutableListOf<String>("none", "none", "none", "none", "none", "none", "none")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoalBinding.inflate(layoutInflater)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        // 오늘 날짜
        today = LocalDate.now()

        // 요일
        val dayOfWeek: DayOfWeek = today.getDayOfWeek()
        //val todayOfWeek = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
        val todayOfWeekNum = dayOfWeek.value // 요일 1~7
        startDay = today.minusDays((todayOfWeekNum - 1).toLong())

        // 이번주
        setDate(startDay)

        // 저번주
        binding.btnMoveWeekLeft.setOnClickListener {
            startDay = startDay.minusDays(7L)
            setDate(startDay)
        }
        // 다음주
        binding.btnMoveWeekRight.setOnClickListener {
            startDay = startDay.plusDays(7L)
            setDate(startDay)
        }

        // 실천사항 추가
        binding.textAddTodoBtn.setOnClickListener(View.OnClickListener {
            view.findNavController().navigate(R.id.action_menu_goal_to_add)
        })

        // 실천사항 확인
        binding.linearShowAllTodo.setOnClickListener(View.OnClickListener {
            view.findNavController().navigate(R.id.action_menu_goal_to_view)
        })

        addTodoContent(TodoOfDayItem(today, "라떼 아트", false))
        if(checkAdd)  addTodoContent(TodoOfDayItem(today, "오전 10:00 실기 학원", false))
    }
    private fun initRecycler() {
        dayOfWeekItems = mutableListOf<DayOfWeekItem>()

        // 날짜 리스트 recyclerview 세팅
        dayOfWeekAdapter = DayOfWeekAdapter(
            requireContext()
        )
        binding.recyclerviewGoalDate.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewGoalDate.adapter = dayOfWeekAdapter
        binding.recyclerviewGoalDate.isNestedScrollingEnabled = false
        dayOfWeekAdapter.items = dayOfWeekItems!!

        todoOfDayItems = mutableListOf<TodoOfDayItem>()

        // 실천 내용 recyclerview 세팅
        todoOfDayAdapter = TodoOfDayAdapter(
            requireContext(),
            onClickLatte = {
                checkLatte()
            }
        )
        binding.recyclerviewTodoContent.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewTodoContent.adapter = todoOfDayAdapter
        binding.recyclerviewTodoContent.isNestedScrollingEnabled = false
        todoOfDayAdapter.items = todoOfDayItems!!
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setDate(startDay: LocalDate) {
        dayOfWeekItems = mutableListOf<DayOfWeekItem>()
        dayOfWeekAdapter.items = dayOfWeekItems!!
        dayOfWeekAdapter.notifyDataSetChanged()

        // 주차 표시
        val weekOfMonth: String = startDay.month.value.toString() + "월 " + getWeekOfMonth(startDay).toString() + "주차"
        binding.textWeekOfMonth.text = weekOfMonth

        for(i: Long in 0L..6L) {
            val date = startDay.plusDays(i)
            if(date < today) {
                addDay(
                    DayOfWeekItem(
                        date,            // 날짜
                        date.dayOfMonth, // 일
                        date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN), // 요일
                        progressValues[i.toInt()],
                    )
                )
            }
            else if(date == today) {
                addDay(
                    DayOfWeekItem(
                        date,            // 날짜
                        date.dayOfMonth, // 일
                        date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN), // 요일
                        progressValues[i.toInt()],
                        true
                    )
                )
            }
            else {
                addDay(
                    DayOfWeekItem(
                        date,            // 날짜
                        date.dayOfMonth, // 일
                        date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN), // 요일
                        "none"
                    )
                )
            }
        }
    }

    private fun checkLatte() {
        dayOfWeekItems!![6].progress = "some"
        dayOfWeekAdapter.notifyDataSetChanged()
    }

    // 주차 계산
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getWeekOfMonth(startDay: LocalDate): Int {
        return ((startDay.dayOfMonth - 1) / 7).toInt() + 1
    }

    private fun addDay(day: DayOfWeekItem) {
        dayOfWeekItems!!.add(day)
        dayOfWeekAdapter.notifyDataSetChanged()
    }

    private fun addTodoContent(todo: TodoOfDayItem) {
        todoOfDayItems!!.add(todo)
        todoOfDayAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}