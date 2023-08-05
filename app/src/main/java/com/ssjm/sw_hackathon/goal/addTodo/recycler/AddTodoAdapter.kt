package com.ssjm.sw_hackathon.goal.addTodo.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.ItemAddTodoBinding

class AddTodoAdapter (private val context: Context,
) : RecyclerView.Adapter<AddTodoAdapter.AddTodoViewHolder>() {

    var items = mutableListOf<AddTodoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            AddTodoViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_add_todo, parent, false)

        return AddTodoViewHolder(ItemAddTodoBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AddTodoViewHolder, position: Int) {
        holder.bind(items[position])


        /*holder.itemView.setOnClickListener {

        }*/
    }

    inner class AddTodoViewHolder(private val binding: ItemAddTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AddTodoItem) {

            // 월요일 선택
            binding.textMonday.setOnClickListener(View.OnClickListener {
                if(item.days[0] === false) {
                    item.days[0] = true

                    binding.textMonday.setBackgroundResource(R.drawable.shape_select_day_selected)
                    binding.textMonday.setTextColor(context.resources.getColor(R.color.white))
                }
                else {
                    item.days[0] = false

                    binding.textMonday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                    binding.textMonday.setTextColor(context.resources.getColor(R.color.main_color_1))
                }
                checkSelected(item)
            })

            // 화요일 선택
            binding.textTuesday.setOnClickListener(View.OnClickListener {
                if(item.days[1] === false) {
                    item.days[1] = true

                    binding.textTuesday.setBackgroundResource(R.drawable.shape_select_day_selected)
                    binding.textTuesday.setTextColor(context.resources.getColor(R.color.white))
                }
                else {
                    item.days[1] = false

                    binding.textTuesday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                    binding.textTuesday.setTextColor(context.resources.getColor(R.color.main_color_1))
                }
                checkSelected(item)
            })

            // 수요일 선택
            binding.textWednesday.setOnClickListener(View.OnClickListener {
                if(item.days[2] === false) {
                    item.days[2] = true

                    binding.textWednesday.setBackgroundResource(R.drawable.shape_select_day_selected)
                    binding.textWednesday.setTextColor(context.resources.getColor(R.color.white))
                }
                else {
                    item.days[2] = false

                    binding.textWednesday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                    binding.textWednesday.setTextColor(context.resources.getColor(R.color.main_color_1))
                }
                checkSelected(item)
            })

            // 목요일 선택
            binding.textThursday.setOnClickListener(View.OnClickListener {
                if(item.days[3] === false) {
                    item.days[3] = true

                    binding.textThursday.setBackgroundResource(R.drawable.shape_select_day_selected)
                    binding.textThursday.setTextColor(context.resources.getColor(R.color.white))
                }
                else {
                    item.days[3] = false

                    binding.textThursday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                    binding.textThursday.setTextColor(context.resources.getColor(R.color.main_color_1))
                }
                checkSelected(item)
            })

            // 금요일 선택
            binding.textFriday.setOnClickListener(View.OnClickListener {
                if(item.days[4] === false) {
                    item.days[4] = true

                    binding.textFriday.setBackgroundResource(R.drawable.shape_select_day_selected)
                    binding.textFriday.setTextColor(context.resources.getColor(R.color.white))
                }
                else {
                    item.days[4] = false

                    binding.textFriday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                    binding.textFriday.setTextColor(context.resources.getColor(R.color.main_color_1))
                }
                checkSelected(item)
            })

            // 토요일 선택
            binding.textSaturday.setOnClickListener(View.OnClickListener {
                if(item.days[5] === false) {
                    item.days[5] = true

                    binding.textSaturday.setBackgroundResource(R.drawable.shape_select_day_selected)
                    binding.textSaturday.setTextColor(context.resources.getColor(R.color.white))
                }
                else {
                    item.days[5] = false

                    binding.textSaturday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                    binding.textSaturday.setTextColor(context.resources.getColor(R.color.main_color_1))
                }
                checkSelected(item)
            })

            // 일요일 선택
            binding.textSunday.setOnClickListener(View.OnClickListener {
                if(item.days[6] === false) {
                    item.days[6] = true

                    binding.textSunday.setBackgroundResource(R.drawable.shape_select_day_selected)
                    binding.textSunday.setTextColor(context.resources.getColor(R.color.white))
                }
                else {
                    item.days[6] = false

                    binding.textSunday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                    binding.textSunday.setTextColor(context.resources.getColor(R.color.main_color_1))
                }
                checkSelected(item)
            })

        }
    }

    private fun checkSelected(item: AddTodoItem) {
        item.checked = false
        for(i: Int in 0..7) {
            if(item.days[i]) {
                item.checked = true
                break
            }
        }
    }
}