package com.ssjm.sw_hackathon.home.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.ItemTodoDayBinding
import com.ssjm.sw_hackathon.databinding.ItemTodoOfHomeBinding

class HomeTodoDayAdapter (private val context: Context,
) : RecyclerView.Adapter<HomeTodoDayAdapter.HomeTodoDayViewHolder>() {

    var items = mutableListOf<String>()

    fun build(days: MutableList<String>?): HomeTodoDayAdapter {
        if(days != null)
            items = days

        return this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            HomeTodoDayViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_todo_day, parent, false)

        return HomeTodoDayViewHolder(ItemTodoDayBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HomeTodoDayViewHolder, position: Int) {
        holder.bind(items[position])

    }

    inner class HomeTodoDayViewHolder(private val binding: ItemTodoDayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.textTodoDay.text = item
        }
    }

}