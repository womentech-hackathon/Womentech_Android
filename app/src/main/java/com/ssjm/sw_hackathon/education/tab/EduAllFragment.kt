package com.ssjm.sw_hackathon.education.tab

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentEduAllBinding
import com.ssjm.sw_hackathon.education.bottomsheet.EduOrderBottomFragment
import com.ssjm.sw_hackathon.education.recycler.EducationAdapter
import com.ssjm.sw_hackathon.education.recycler.EducationItem
import com.ssjm.sw_hackathon.education.recycler.EducationItemInterface
import com.ssjm.sw_hackathon.education.recycler.LoadingItem
import com.ssjm.sw_hackathon.educationApi.EducationRow
import com.ssjm.sw_hackathon.educationApi.apiGetEducationCount
import com.ssjm.sw_hackathon.educationApi.apiGetEducationInfo

// 교육 > 전체
class EduAllFragment : Fragment(), EduOrderBottomFragment.EduOrderListener {
    // ViewBinding Setting
    private var _binding: FragmentEduAllBinding? = null
    private val binding get() = _binding!!

    // 전체
    private var allEducationItems: MutableList<EducationItemInterface>? = null
    // 접수중
    private var ingEducationItems: MutableList<EducationItemInterface>? = null
    // 마감
    private var endEducationItems: MutableList<EducationItemInterface>? = null

    // RecyclerView Adapter
    private lateinit var educationAdapter: EducationAdapter

    // 선택된 filter: all, ing, end
    private var filter = "all"

    // 정렬 방식: new, old, end
    private var orderType = "new"

    // 현재 페이지
    private var page = 1

    override fun onAttach(context: Context) {
        super.onAttach(context)

        page = 1

        // 서울시 어르신 취업지원센터 교육정보 조회
        apiGetEducationCount(
            addEducationCount = {
                getEducation(it)
            }
        )
    }

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

        // 서울시 어르신 취업지원센터 교육정보 조회
        /*apiGetEducationCount(
            addEducationCount = {
                getEducation(it)
            }
        )*/

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

        // 정렬 버튼
        binding.linearSortingBtn.setOnClickListener(View.OnClickListener {
            val bottomSheet = EduOrderBottomFragment().apply { setListener(this@EduAllFragment) }
            bottomSheet.show(requireActivity().supportFragmentManager, bottomSheet.tag)
        })

        // recyclerview 스크롤 감지 => 무한 스크롤
        binding.recyclerviewEduAll.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // 스크롤이 끝에 도달했는지 확인
                if (!binding.recyclerviewEduAll.canScrollVertically(1)) {
                    // 모두 보기 filter 중이라면 모두보기 list의 데이터를 추가
                    if(filter == "all" && allEducationItems!!.size >= 10) {
                        educationAdapter.items.removeAt(10 * page)
                        page++
                        educationAdapter.items = allEducationItems!!.subList(0, 10 * page)
                        if(allEducationItems!!.size > 10 * page) {
                            educationAdapter.items.add(LoadingItem())
                        }
                        educationAdapter.notifyDataSetChanged()
                    }
                    // 접수중 filter 중이라면 접수중 list의 데이터를 추가
                    else if(filter == "ing" && ingEducationItems!!.size >= 10) {
                        educationAdapter.items.removeAt(10 * page)
                        page++
                        educationAdapter.items = ingEducationItems!!.subList(0, 10 * page)
                        if(ingEducationItems!!.size > 10 * page) {
                            educationAdapter.items.add(LoadingItem())
                        }
                        educationAdapter.notifyDataSetChanged()
                    }
                    // 마감 filter 중이라면 마감 list의 데이터를 추가
                    else if(filter == "end" && endEducationItems!!.size >= 10) {
                        educationAdapter.items.removeAt(10 * page)
                        page++
                        educationAdapter.items = endEducationItems!!.subList(0, 10 * page)
                        if(endEducationItems!!.size > 10 * page) {
                            educationAdapter.items.add(LoadingItem())
                        }
                        educationAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    // 교육 아이템 recyclerview 세팅
    private fun initRecycler() {
        allEducationItems = mutableListOf<EducationItemInterface>()
        ingEducationItems = mutableListOf<EducationItemInterface>()
        endEducationItems = mutableListOf<EducationItemInterface>()

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
                        applicationStart = education.APPLICATIONSTARTDATE,
                        applicationEnd = education.APPLICATIONSTARTDATE,
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

            // adapter 새로고침
            educationAdapter.notifyDataSetChanged()
        }
    }

    // 모두 보기 필터 선택
    fun filterAll() {
        page = 1
        binding.recyclerviewEduAll.smoothScrollToPosition(0)

        filter = "all"
        if(allEducationItems!!.size >= 10 * page) {
            educationAdapter.items = allEducationItems!!.subList(0, 10 * page)
            if(allEducationItems!!.size > 10 * page) {
                educationAdapter.items.add(LoadingItem())
            }
        }
        else
            educationAdapter.items = allEducationItems!!
        educationAdapter.notifyDataSetChanged()

        // 모두보기 선택
        binding.linearEduFilterAll.setBackgroundResource(R.drawable.shape_select_day_selected)
        binding.textEduFilterAll.setTextColor(requireContext().getColor(R.color.white))

        // 접수중 해제
        binding.linearEduFilterIng.setBackgroundResource(R.drawable.shape_select_day_unselected)
        binding.textEduFilterIng.setTextColor(requireContext().getColor(R.color.gray05))

        // 마감 해제
        binding.linearEduFilterEnd.setBackgroundResource(R.drawable.shape_select_day_unselected)
        binding.textEduFilterEnd.setTextColor(requireContext().getColor(R.color.gray05))
    }

    // 접수중 필터 선택
    fun filterIng() {
        page = 1
        binding.recyclerviewEduAll.smoothScrollToPosition(0)

        filter = "ing"
        if(ingEducationItems!!.size >= 10 * page) {
            educationAdapter.items = ingEducationItems!!.subList(0, 10 * page)
            if(ingEducationItems!!.size > 10 * page) {
                educationAdapter.items.add(LoadingItem())
            }
        }
        else
            educationAdapter.items = ingEducationItems!!
        educationAdapter.notifyDataSetChanged()

        // 모두보기 해제
        binding.linearEduFilterAll.setBackgroundResource(R.drawable.shape_select_day_unselected)
        binding.textEduFilterAll.setTextColor(requireContext().getColor(R.color.gray05))

        // 접수중 선택
        binding.linearEduFilterIng.setBackgroundResource(R.drawable.shape_select_day_selected)
        binding.textEduFilterIng.setTextColor(requireContext().getColor(R.color.white))

        // 마감 해제
        binding.linearEduFilterEnd.setBackgroundResource(R.drawable.shape_select_day_unselected)
        binding.textEduFilterEnd.setTextColor(requireContext().getColor(R.color.gray05))
    }

    // 마감 필터 선택
    fun filterEnd() {
        page = 1
        binding.recyclerviewEduAll.smoothScrollToPosition(0)

        filter = "end"
        if(endEducationItems!!.size >= 10 * page) {
            educationAdapter.items = endEducationItems!!.subList(0, 10 * page)
            if(endEducationItems!!.size > 10 * page) {
                educationAdapter.items.add(LoadingItem())
            }
        }
        else
            educationAdapter.items = endEducationItems!!
        educationAdapter.notifyDataSetChanged()

        // 모두보기 해제
        binding.linearEduFilterAll.setBackgroundResource(R.drawable.shape_select_day_unselected)
        binding.textEduFilterAll.setTextColor(requireContext().getColor(R.color.gray05))

        // 접수중 해제
        binding.linearEduFilterIng.setBackgroundResource(R.drawable.shape_select_day_unselected)
        binding.textEduFilterIng.setTextColor(requireContext().getColor(R.color.gray05))

        // 마감 선택
        binding.linearEduFilterEnd.setBackgroundResource(R.drawable.shape_select_day_selected)
        binding.textEduFilterEnd.setTextColor(requireContext().getColor(R.color.white))
    }

    // 최신순 정렬
    override fun orderNew() {
        orderType = "new"
        allEducationItems!!.sortByDescending { it.applicationStart }
        ingEducationItems!!.sortByDescending { it.applicationStart }
        endEducationItems!!.sortByDescending { it.applicationStart }

        if(filter == "all") filterAll()
        else if(filter == "ing") filterIng()
        else if(filter == "end") filterEnd()
    }

    // 오래된순 정렬
    override fun orderOld() {
        orderType = "old"
        allEducationItems!!.sortBy { it.applicationStart }
        ingEducationItems!!.sortBy { it.applicationStart }
        endEducationItems!!.sortBy { it.applicationStart }

        if(filter == "all") filterAll()
        else if(filter == "ing") filterIng()
        else if(filter == "end") filterEnd()
    }

    // 마감순 정렬
    override fun orderEnd() {
        orderType = "end"
        allEducationItems!!.sortByDescending { it.applicationEnd }
        ingEducationItems!!.sortByDescending { it.applicationEnd }
        endEducationItems!!.sortByDescending { it.applicationEnd }

        allEducationItems!!.sortByDescending { it.status }
        ingEducationItems!!.sortByDescending { it.status }
        endEducationItems!!.sortByDescending { it.status }

        if(filter == "all") filterAll()
        else if(filter == "ing") filterIng()
        else if(filter == "end") filterEnd()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}