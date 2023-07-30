package com.ssjm.sw_hackathon.education.recycler

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.ItemEducationBinding
import okhttp3.internal.notify

// 전체 교육 Recycler Adapter
class EducationAdapter (
    private val context: Context,
    ) : RecyclerView.Adapter<EducationAdapter.EducationViewHolder>() {

    var items = mutableListOf<EducationItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            EducationViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_education, parent, false)

        return EducationViewHolder(ItemEducationBinding.bind(view))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        holder.bind(items[position])

        // 링크 아이템 클릭 시
        /*holder.itemView.setOnClickListener {
            onClickLinkItem(items[position].memoNum)
        }*/
    }

    inner class EducationViewHolder(private val binding: ItemEducationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EducationItem) {

            // 진행중 or 마감
            binding.textEduStatus.text = item.status
            // 진행중, 마감 상태 색상
            // 진행중 = 파란색
            if(item.status == "접수중") {
                // 글자색
                binding.textEduStatus.setTextColor(context.resources.getColor(R.color.black01))

                // 아이콘 색
                binding.iconEduStatus.setImageResource(R.drawable.ic_education_status_ing)

                // 배경색
                binding.backgroundBox.setBackgroundResource(R.drawable.shape_edu_background_blue)
            }
            // 마감 = 회색
            if(item.status == "마감") {
                // 글자색
                binding.textEduStatus.setTextColor(context.resources.getColor(R.color.gray04))

                // 아이콘 색
                binding.iconEduStatus.setImageResource(R.drawable.ic_education_status_end)

                // 배경색
                binding.backgroundBox.setBackgroundResource(R.drawable.shape_edu_background_gray)
            }

            // 교육 제목
            binding.textEduTitle.text = item.title

            // 신청기간
            binding.textEduApplication.text = item.applicationPeriod

            // 교육기간
            binding.textEduEducation.text = item.educationPeriod

            // 찜 유무 표시
            if(item.isBookmark) {
                // 버튼 배경
                binding.linearBookmarkBtn.setBackgroundResource(R.drawable.shape_edu_bookmark_selected)

                // 찜 글자 색상
                binding.textBookmarkBtn.setTextColor(context.resources.getColor(R.color.white))

                // 아이콘 모양
                binding.iconBookmarkBtn.setImageResource(R.drawable.ic_bookmark_selected)
            }
            else {
                // 버튼 배경
                binding.linearBookmarkBtn.setBackgroundResource(R.drawable.shape_edu_bookmark_unselected)

                // 찜 글자 색상
                binding.textBookmarkBtn.setTextColor(context.resources.getColor(R.color.main_color_1))

                // 아이콘 모양
                binding.iconBookmarkBtn.setImageResource(R.drawable.ic_bookmark_unselected)
            }

            // 북마크 버튼 클릭시
            binding.linearBookmarkBtn.setOnClickListener(View.OnClickListener {
                // 미선택 -> 선택
                if(!item.isBookmark) {
                    // 버튼 배경
                    binding.linearBookmarkBtn.setBackgroundResource(R.drawable.shape_edu_bookmark_selected)

                    // 찜 글자 색상
                    binding.textBookmarkBtn.setTextColor(context.resources.getColor(R.color.white))

                    // 아이콘 모양
                    binding.iconBookmarkBtn.setImageResource(R.drawable.ic_bookmark_selected)
                }

                // 선택 -> 미선택
                else {
                    // 버튼 배경
                    binding.linearBookmarkBtn.setBackgroundResource(R.drawable.shape_edu_bookmark_unselected)

                    // 찜 글자 색상
                    binding.textBookmarkBtn.setTextColor(context.resources.getColor(R.color.main_color_1))

                    // 아이콘 모양
                    binding.iconBookmarkBtn.setImageResource(R.drawable.ic_bookmark_unselected)
                }

                item.isBookmark = !item.isBookmark
            })
        }
    }
}