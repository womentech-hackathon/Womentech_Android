package com.ssjm.sw_hackathon.education.tab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentEduAllBinding
import com.ssjm.sw_hackathon.education.recycler.EducationAdapter
import com.ssjm.sw_hackathon.education.recycler.EducationItem
import com.ssjm.sw_hackathon.educationApi.EducationRow
import com.ssjm.sw_hackathon.educationApi.apiGetEducationCount
import com.ssjm.sw_hackathon.educationApi.apiGetEducationInfo
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

// 교육 > 전체
class EduAllFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentEduAllBinding? = null
    private val binding get() = _binding!!

    // 전체
    private var allEducationItems: MutableList<EducationItem>? = null
    // 접수중
    private var ingEducationItems: MutableList<EducationItem>? = null
    // 마감
    private var endEducationItems: MutableList<EducationItem>? = null

    // RecyclerView Adapter
    private lateinit var educationAdapter: EducationAdapter

    // 선택된 filter: all, ing, end
    private var filter = "all"

    // 정렬 방식: new, old, end
    private var orderType = "new"

    // 현재 페이지
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEduAllBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerview 세팅
        initRecycler()

        // 서울시 어르신 취업지원센터 교육정보 개수 조회
        apiGetEducationCount(
            addEducationCount = {
                getEducation(it)
            }
        )

        // 모두보기 필터 선택
        binding.linearEduFilterAll.setOnClickListener(View.OnClickListener {
            filterAll()
        })

        // 접수중 필터 선택
        binding.linearEduFilterIng.setOnClickListener(View.OnClickListener {
            filterIng()
        })

        // 마감 필터 선택
        binding.linearEduFilterEnd.setOnClickListener(View.OnClickListener {
            filterEnd()
        })

        // recyclerview 스크롤 감지 => 무한 스크롤
    }

    // 교육 아이템 recyclerview 세팅
    private fun initRecycler() {
        allEducationItems = mutableListOf<EducationItem>()
        ingEducationItems = mutableListOf<EducationItem>()
        endEducationItems = mutableListOf<EducationItem>()

        educationAdapter = EducationAdapter(requireContext())
        binding.recyclerviewEduAll.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewEduAll.adapter = educationAdapter
        binding.recyclerviewEduAll.isNestedScrollingEnabled = false // 스크롤을 매끄럽게 해줌

        // 최초: 전체 보여주기
        educationAdapter.items = allEducationItems!!
    }

    fun getEducation(count: Int) {
        // 처음 보여줄 교육 데이터 10개 가져와서 나열
        apiGetEducationInfo(
            1,
            count,
            addEducationList = {
                addEducationItems(it)
            }
        )
    }

    // 데이터 세팅
    fun addEducationItems(educationItems: MutableList<EducationRow>?) {
        if(educationItems != null) {
            for(education in educationItems) {
                allEducationItems!!.add(
                    EducationItem(
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
                        isBookmark = false  // 찜 유무
                    )
                )
                if(education.APPLY_STATE == "접수중") {
                    ingEducationItems!!.add(
                        allEducationItems!!.last()
                    )
                }
                else if(education.APPLY_STATE == "마감") {
                    endEducationItems!!.add(
                        allEducationItems!!.last()
                    )
                }
            }

            // 최초 접속 시 첫 페이지만
            page = 1

            // 최신순
            orderNew()

            // 최초 접속 시 모두 보기
            filterAll()

            // adapter 새로고침
            educationAdapter.notifyDataSetChanged()
        }
    }

    // 모두 보기 필터 선택
    fun filterAll() {
        page = 1
        binding.recyclerviewEduAll.smoothScrollToPosition(0)

        filter = "all"
        if(allEducationItems!!.size >= 10)
            educationAdapter.items = allEducationItems!!.subList(0, 10 * page)
        else
            educationAdapter.items = allEducationItems!!
        educationAdapter.notifyDataSetChanged()

        // 모두보기 선택
        binding.linearEduFilterAll.setBackgroundResource(R.drawable.shape_edu_filter_blue)
        binding.textEduFilterAll.setTextColor(requireContext().getColor(R.color.white))

        // 접수중 해제
        binding.linearEduFilterIng.setBackgroundResource(R.drawable.shape_edu_filter_gray)
        binding.textEduFilterIng.setTextColor(requireContext().getColor(R.color.gray05))

        // 마감 해제
        binding.linearEduFilterEnd.setBackgroundResource(R.drawable.shape_edu_filter_gray)
        binding.textEduFilterEnd.setTextColor(requireContext().getColor(R.color.gray05))
    }

    // 접수중 필터 선택
    fun filterIng() {
        page = 1
        binding.recyclerviewEduAll.smoothScrollToPosition(0)

        filter = "ing"
        if(ingEducationItems!!.size >= 10)
            educationAdapter.items = ingEducationItems!!.subList(0, 10 * page)
        else
            educationAdapter.items = ingEducationItems!!
        educationAdapter.notifyDataSetChanged()

        // 모두보기 해제
        binding.linearEduFilterAll.setBackgroundResource(R.drawable.shape_edu_filter_gray)
        binding.textEduFilterAll.setTextColor(requireContext().getColor(R.color.gray05))

        // 접수중 선택
        binding.linearEduFilterIng.setBackgroundResource(R.drawable.shape_edu_filter_blue)
        binding.textEduFilterIng.setTextColor(requireContext().getColor(R.color.white))

        // 마감 해제
        binding.linearEduFilterEnd.setBackgroundResource(R.drawable.shape_edu_filter_gray)
        binding.textEduFilterEnd.setTextColor(requireContext().getColor(R.color.gray05))
    }

    // 마감 필터 선택
    fun filterEnd() {
        page = 1
        binding.recyclerviewEduAll.smoothScrollToPosition(0)

        filter = "end"
        if(endEducationItems!!.size >= 10)
            educationAdapter.items = endEducationItems!!.subList(0, 10 * page)
        else
            educationAdapter.items = endEducationItems!!
        educationAdapter.notifyDataSetChanged()

        // 모두보기 해제
        binding.linearEduFilterAll.setBackgroundResource(R.drawable.shape_edu_filter_gray)
        binding.textEduFilterAll.setTextColor(requireContext().getColor(R.color.gray05))

        // 접수중 해제
        binding.linearEduFilterIng.setBackgroundResource(R.drawable.shape_edu_filter_gray)
        binding.textEduFilterIng.setTextColor(requireContext().getColor(R.color.gray05))

        // 마감 선택
        binding.linearEduFilterEnd.setBackgroundResource(R.drawable.shape_edu_filter_blue)
        binding.textEduFilterEnd.setTextColor(requireContext().getColor(R.color.white))
    }

    // 최신순 정렬
    fun orderNew() {
        orderType = "new"
        allEducationItems!!.sortByDescending { it.applicationPeriod }
        ingEducationItems!!.sortByDescending { it.applicationPeriod }
        endEducationItems!!.sortByDescending { it.applicationPeriod }
    }

    // 오래된순 정렬
    fun orderOld() {
        orderType = "old"
    }

    // 마감순 정렬
    fun orderEnd() {
        orderType = "end"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}