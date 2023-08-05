package com.ssjm.sw_hackathon.goal.tab.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.ItemViewTodoDoneBinding

class ViewTodoDoneAdapter (private val context: Context,
) : RecyclerView.Adapter<ViewTodoDoneAdapter.ViewTodoIngViewHolder>() {

    var items = mutableListOf<ViewTodoDoneItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            ViewTodoIngViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_view_todo_done, parent, false)

        return ViewTodoIngViewHolder(ItemViewTodoDoneBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewTodoIngViewHolder, position: Int) {
        holder.bind(items[position])


        /*holder.itemView.setOnClickListener {

        }*/
    }

    inner class ViewTodoIngViewHolder(private val binding: ItemViewTodoDoneBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ViewTodoDoneItem) {
            binding.textViewTodoDoneTitle.text = item.title

            binding.textStartDate.text = item.startDate
            binding.textFinishDate.text = item.finishDate

            // 요일 리스트 보여줄 recyclerview 세팅
            binding.recyclerviewViewTodoDoneDay.apply {
                adapter = ViewTodoDayAdapter(context).build(item.days)
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }
}