package com.ssjm.sw_hackathon.goal.tab.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.ItemViewTodoDayBinding

class ViewTodoDayAdapter (private val context: Context,
) : RecyclerView.Adapter<ViewTodoDayAdapter.ViewTodoDayViewHolder>() {

    var items = mutableListOf<String>()

    fun build(days: MutableList<String>?): ViewTodoDayAdapter {
        if(days != null)
            items = days

        return this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            ViewTodoDayViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_view_todo_day, parent, false)

        return ViewTodoDayViewHolder(ItemViewTodoDayBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewTodoDayViewHolder, position: Int) {
        holder.bind(items[position])

    }

    inner class ViewTodoDayViewHolder(private val binding: ItemViewTodoDayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.textMonday.text = item
        }
    }
}