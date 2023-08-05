package com.ssjm.sw_hackathon.goal.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.ItemTodoContentsBinding

class TodoOfDayAdapter (private val context: Context,
) : RecyclerView.Adapter<TodoOfDayAdapter.TodoOfDayViewHolder>() {

    var items = mutableListOf<TodoOfDayItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            TodoOfDayViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_todo_contents, parent, false)

        return TodoOfDayViewHolder(ItemTodoContentsBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TodoOfDayViewHolder, position: Int) {
        holder.bind(items[position])


        /*holder.itemView.setOnClickListener {

        }*/
    }

    inner class TodoOfDayViewHolder(private val binding: ItemTodoContentsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TodoOfDayItem) {
            binding.textTodoContent.text = item.content

            if(item.checked) {
                binding.imgTodoCompleteBtnRound.setImageResource(R.drawable.ic_check_round_selected)
            }
            else {
                binding.imgTodoCompleteBtnRound.setImageResource(R.drawable.ic_check_round_unselected)
            }

            binding.relativeTodoCompleteBtn.setOnClickListener(View.OnClickListener {
                item.checked = !item.checked
                notifyDataSetChanged()
            })
        }
    }
}