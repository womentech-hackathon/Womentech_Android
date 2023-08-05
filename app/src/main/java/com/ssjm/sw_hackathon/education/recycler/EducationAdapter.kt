package com.ssjm.sw_hackathon.education.recycler

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.ItemEducationBinding
import com.ssjm.sw_hackathon.databinding.ItemLoadingBinding
import com.ssjm.sw_hackathon.educationApi.bookmark.apiAddBookmark
import com.ssjm.sw_hackathon.educationApi.bookmark.apiDeleteBookmark
import okhttp3.internal.notify

// 전체 교육 Recycler Adapter
class EducationAdapter (
    private val context: Context,
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var educationBinding: ItemEducationBinding
    private lateinit var loadingBinding: ItemLoadingBinding

    var items = mutableListOf<EducationItemInterface>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)  = when (viewType) {
        // 교육
        TYPE_EDUCATION -> {
            val view = LayoutInflater.from(context).inflate(R.layout.item_education, parent, false)
            educationBinding = ItemEducationBinding.bind(view)

            EducationViewHolder(ItemEducationBinding.bind(view))
        }
        // 로딩
        TYPE_LOADING -> {
            val view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false)
            loadingBinding = ItemLoadingBinding.bind(view)

            LoadingViewHolder(ItemLoadingBinding.bind(view))
        }
        else -> {
            throw IllegalStateException("Not Found ViewHolder Type $viewType")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int) = when (items[position]) {
        // 교육
        is EducationItem -> {
            TYPE_EDUCATION
        }
        // 로딩
        is LoadingItem -> {
            TYPE_LOADING
        }
        else -> {
            throw IllegalStateException("Not Found ViewHolder Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            // 교육
            is EducationViewHolder -> {
                holder.bind(items[position] as EducationItem)

                // 링크 아이템 클릭 시
                /*holder.itemView.setOnClickListener {
                onClickLinkItem(items[position].memoNum)
                }*/
            }
            // 로딩
            is LoadingViewHolder -> {

            }

        }
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

                    // 북마크 추가
                    apiAddBookmark(item.eduNumber)
                }

                // 선택 -> 미선택
                else {
                    // 버튼 배경
                    binding.linearBookmarkBtn.setBackgroundResource(R.drawable.shape_edu_bookmark_unselected)

                    // 찜 글자 색상
                    binding.textBookmarkBtn.setTextColor(context.resources.getColor(R.color.main_color_1))

                    // 아이콘 모양
                    binding.iconBookmarkBtn.setImageResource(R.drawable.ic_bookmark_unselected)

                    // 북마크 삭제
                    if(item.bookmarkId != null) apiDeleteBookmark(item.bookmarkId)
                }

                item.isBookmark = !item.isBookmark
            })
        }
    }

    inner class LoadingViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    companion object {
        private const val TYPE_EDUCATION = 0  // 내용
        private const val TYPE_LOADING = 1    // 로딩
    }
}