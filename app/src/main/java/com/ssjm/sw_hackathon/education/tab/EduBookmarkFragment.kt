package com.ssjm.sw_hackathon.education.tab

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.ssjm.sw_hackathon.databinding.FragmentEduBookmarkBinding
import com.ssjm.sw_hackathon.education.recycler.EducationAdapter
import com.ssjm.sw_hackathon.education.recycler.EducationItem
import com.ssjm.sw_hackathon.education.recycler.EducationItemInterface
import com.ssjm.sw_hackathon.educationApi.bookmark.apiGetBookmark
import com.ssjm.sw_hackathon.educationApi.bookmark.getBookmark.GetBookmarks
import com.ssjm.sw_hackathon.educationApi.openApi.EducationRow
import com.ssjm.sw_hackathon.educationApi.openApi.apiGetEducationCount
import com.ssjm.sw_hackathon.educationApi.openApi.apiGetEducationInfo

// 교육 > 찜
class EduBookmarkFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentEduBookmarkBinding? = null
    private val binding get() = _binding!!

    // 전체
    private var bookmarkEducationItems: MutableList<EducationItemInterface>? = null

    // RecyclerView Adapter
    private lateinit var educationAdapter: EducationAdapter

    // 현재 페이지
    private var page = 1

    private var bookmarkList: MutableList<GetBookmarks>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        page = 1
        bookmarkList = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEduBookmarkBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerview 세팅
        initRecycler()

        apiGetBookmark(
            getBookmark = {
                getBookmark(it)
            }
        )

        binding.textBookmarkNoticeCount.text = "총 0건이 있습니다."
    }

    // 교육 아이템 recyclerview 세팅
    private fun initRecycler() {
        bookmarkEducationItems = mutableListOf<EducationItemInterface>()

        educationAdapter = EducationAdapter(requireContext())
        binding.recyclerviewEduBookmark.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewEduBookmark.adapter = educationAdapter
        binding.recyclerviewEduBookmark.isNestedScrollingEnabled = false // 스크롤을 매끄럽게 해줌

        educationAdapter.items = bookmarkEducationItems!!
    }

    private fun getBookmark(bookmarks: MutableList<GetBookmarks>?) {
        if(bookmarks == null) {
            binding.textBookmarkNoticeCount.text = "총 0건이 있습니다."
        }
        else {
            bookmarkList = bookmarks

            binding.textBookmarkNoticeCount.text = "총 " + bookmarks!!.size + "건이 있습니다."

            apiGetEducationCount(
                addEducationCount = {
                    getCountEdu(it)
                }
            )
        }
    }

    private fun getCountEdu(count: Int) {
        apiGetEducationInfo(
            1,
            count,
            addEducationList = {
                addEducationItems(it)
            }
        )
    }

    private fun addEducationItems(educationItems: MutableList<EducationRow>?) {
        if(educationItems != null) {
            for(education in educationItems) {
                val bookmarkId = checkBookmark(education)
                if(bookmarkId != -1) {
                    bookmarkEducationItems!!.add(
                        EducationItem(
                            bookmarkId = bookmarkId,
                            eduNumber = education.IDX.toInt(),
                            status = education.APPLY_STATE, // 모집중 or 마감
                            title = education.SUBJECT,      // 교육 제목
                            applicationPeriod
                            = "신청기간: "
                                    + education.APPLICATIONSTARTDATE.replace("-", "/")
                                    + " ~ "
                                    + education.APPLICATIONENDDATE.replace("-", "/"), // 신청 기간
                            educationPeriod
                            = "교육기간: "
                                    + education.STARTDATE.replace("-", "/")
                                    + " ~ "
                                    + education.ENDDATE.replace("-", "/"),   // 교육 기간
                            applicationStart = education.APPLICATIONSTARTDATE,
                            applicationEnd = education.APPLICATIONSTARTDATE,
                            isBookmark = true  // 찜 유무
                        )
                    )
                }
            }

            // adapter 새로고침
            educationAdapter.notifyDataSetChanged()
        }
    }

    // 북마크된 정보인지 확인
    private fun checkBookmark(edu: EducationRow): Int {
        for(i: Int in 0..(bookmarkList!!.size - 1)) {
            if(bookmarkList!![i].number == edu.IDX.toInt()) {
                return bookmarkList!![i].id
            }
        }
        return -1
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}