package com.ssjm.sw_hackathon.goal.tab.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.ItemViewTodoIngBinding
import com.ssjm.sw_hackathon.home.recycler.HomeTodoDayAdapter

class ViewTodoIngAdapter (private val context: Context,
) : RecyclerView.Adapter<ViewTodoIngAdapter.ViewTodoIngViewHolder>() {

    var items = mutableListOf<ViewTodoIngItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            ViewTodoIngViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_view_todo_ing, parent, false)

        return ViewTodoIngViewHolder(ItemViewTodoIngBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewTodoIngViewHolder, position: Int) {
        holder.bind(items[position])


        /*holder.itemView.setOnClickListener {

        }*/
    }

    inner class ViewTodoIngViewHolder(private val binding: ItemViewTodoIngBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ViewTodoIngItem) {
            binding.textViewTodoIngTitle.text = item.title

            binding.textStartDate.text = item.startDate

            // 요일 리스트 보여줄 recyclerview 세팅
            binding.recyclerviewViewTodoIngDay.apply {
                adapter = ViewTodoDayAdapter(context).build(item.days)
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }
}