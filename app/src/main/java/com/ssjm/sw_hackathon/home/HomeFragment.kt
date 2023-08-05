package com.ssjm.sw_hackathon.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentHomeBinding
import com.ssjm.sw_hackathon.education.recycler.EducationAdapter
import com.ssjm.sw_hackathon.education.recycler.EducationItem
import com.ssjm.sw_hackathon.education.recycler.EducationItemInterface
import com.ssjm.sw_hackathon.educationApi.EducationRow
import com.ssjm.sw_hackathon.educationApi.apiGetEducationInfo
import com.ssjm.sw_hackathon.home.recycler.HomeTodoAdapter
import com.ssjm.sw_hackathon.home.recycler.HomeTodoItem

// 메인 탭
class HomeFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // 실천 리스트 recyclerview adapter
    private var homeTodoItems: MutableList<HomeTodoItem>? = null
    private lateinit var homeTodoAdapter: HomeTodoAdapter

    // 전체
    private var bookmarkEducationItems: MutableList<EducationItemInterface>? = null

    // RecyclerView Adapter
    private lateinit var educationAdapter: EducationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        addTodo(HomeTodoItem("note1", "노트에 필사하기", "66일째 실천중", mutableListOf("월", "수")))
        addTodo(HomeTodoItem("barista2", "실기 학원", "32일째 실천중", mutableListOf("화", "목")))

        apiGetEducationInfo(
            20,
            21,
            addEducationList = {
                addEducationItems(it)
            }
        )

        binding.textShowAllEdu.setOnClickListener(View.OnClickListener {
            view?.findNavController()?.navigate(R.id.action_menu_home_to_education)
        })
    }

    // recyclerview 세팅
    private fun initRecycler() {
        homeTodoItems = mutableListOf<HomeTodoItem>()

        // 링크 리스트 recyclerview 세팅
        homeTodoAdapter = HomeTodoAdapter(
            requireContext()
        )
        binding.recyclerviewHomeTodo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewHomeTodo.adapter = homeTodoAdapter
        binding.recyclerviewHomeTodo.isNestedScrollingEnabled = false
        homeTodoAdapter.items = homeTodoItems!!

        bookmarkEducationItems = mutableListOf<EducationItemInterface>()

        educationAdapter = EducationAdapter(requireContext())
        binding.recyclerviewEduBookmark.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewEduBookmark.adapter = educationAdapter
        binding.recyclerviewEduBookmark.isNestedScrollingEnabled = false // 스크롤을 매끄럽게 해줌

        educationAdapter.items = bookmarkEducationItems!!
    }

    private fun addTodo(todo: HomeTodoItem) {
        homeTodoItems!!.add(todo)
        homeTodoAdapter.notifyDataSetChanged()
    }

    private fun addEducationItems(educationItems: MutableList<EducationRow>?) {
        if(educationItems != null) {
            for(education in educationItems) {
                bookmarkEducationItems!!.add(
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
                        isBookmark = true  // 찜 유무
                    )
                )
            }

            // adapter 새로고침
            educationAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}