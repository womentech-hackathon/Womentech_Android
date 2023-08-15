package com.ssjm.sw_hackathon.goal.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.ItemDayOfTodoBinding
import java.time.LocalDate

class DayOfWeekAdapter (private val context: Context,
    private val selectDate: (date: LocalDate) -> Unit,
) : RecyclerView.Adapter<DayOfWeekAdapter.DayOfWeekViewHolder>() {

    var items = mutableListOf<DayOfWeekItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            DayOfWeekViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_day_of_todo, parent, false)

        return DayOfWeekViewHolder(ItemDayOfTodoBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DayOfWeekViewHolder, position: Int) {
        holder.bind(items[position])

        // 날짜 아이템 클릭 시
        holder.itemView.setOnClickListener {
            for(i: Int in 0..(items.size - 1)) {
                if(i == position)
                    items[i].selected = true
                else
                    items[i].selected = false
            }
            notifyDataSetChanged()

            // 날짜 선택
            selectDate(items[position].date)
        }
    }

    inner class DayOfWeekViewHolder(private val binding: ItemDayOfTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DayOfWeekItem) {
            // 요일
            binding.textTodoListDay.text = item.dayOfWeek

            // 일
            binding.textTodoListDate.text = item.day.toString()

            // 달성도
            if(item.progress == "done") {
                binding.textTodoListDate.setTextColor(context.resources.getColor(R.color.white))
                binding.textTodoListDate.setBackgroundResource(R.drawable.shape_todo_round_done)
            }
            else if(item.progress == "fail") {
                binding.textTodoListDate.setTextColor(context.resources.getColor(R.color.white))
                binding.textTodoListDate.setBackgroundResource(R.drawable.shape_todo_round_fail)
            }
            else if(item.progress == "some") {
                binding.textTodoListDate.setTextColor(context.resources.getColor(R.color.white))
                binding.textTodoListDate.setBackgroundResource(R.drawable.shape_todo_round_some)
            }
            else if(item.progress == "none") {
                binding.textTodoListDate.setTextColor(context.resources.getColor(R.color.gray04))
                binding.textTodoListDate.setBackgroundResource(R.drawable.shape_todo_round_none)
            }

            // 선택 유무
            if(item.selected) {
                binding.linearTodoBox.setBackgroundResource(R.drawable.shape_todo_today)
                binding.textTodoListDay.setTextColor(context.resources.getColor(R.color.white))
            }
            else {
                binding.linearTodoBox.setBackgroundResource(R.drawable.shape_todo_today_not)
                binding.textTodoListDay.setTextColor(context.resources.getColor(R.color.black03))
            }
        }
    }
}