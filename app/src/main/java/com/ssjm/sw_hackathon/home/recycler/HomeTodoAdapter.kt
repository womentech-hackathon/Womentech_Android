package com.ssjm.sw_hackathon.home.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.ItemTodoOfHomeBinding

class HomeTodoAdapter (private val context: Context,
) : RecyclerView.Adapter<HomeTodoAdapter.HomeTodoViewHolder>() {

    var items = mutableListOf<HomeTodoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            HomeTodoViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_todo_of_home, parent, false)

        return HomeTodoViewHolder(ItemTodoOfHomeBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HomeTodoViewHolder, position: Int) {
        holder.bind(items[position])

        // 링크 아이템 클릭 시
        /*holder.itemView.setOnClickListener {
            onClickLinkItem(items[position].memoNum)
        }*/
    }

    inner class HomeTodoViewHolder(private val binding: ItemTodoOfHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HomeTodoItem) {

            // 이미지
            val imgResourceName = "img_todo_" + item.coverImage
            val iconResourceId = context.resources.getIdentifier(imgResourceName, "drawable", context.packageName)
            binding.imgTodoCover.setImageResource(iconResourceId)

            // 내용
            binding.textTodoContent.text = item.content

            // 기간
            binding.textTodoPeriod.text = item.period

            // 요일 리스트 보여줄 recyclerview 세팅
            binding.recyclerviewHomeTodoDay.apply {
                adapter = HomeTodoDayAdapter(context).build(item.day)
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

}