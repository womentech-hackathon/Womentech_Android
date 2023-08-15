package com.ssjm.sw_hackathon.home

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.accountApi.apiGetUserName
import com.ssjm.sw_hackathon.databinding.FragmentHomeBinding
import com.ssjm.sw_hackathon.education.recycler.EducationAdapter
import com.ssjm.sw_hackathon.education.recycler.EducationItem
import com.ssjm.sw_hackathon.education.recycler.EducationItemInterface
import com.ssjm.sw_hackathon.educationApi.bookmark.apiGetBookmark
import com.ssjm.sw_hackathon.educationApi.bookmark.getBookmark.GetBookmarks
import com.ssjm.sw_hackathon.educationApi.openApi.EducationRow
import com.ssjm.sw_hackathon.educationApi.openApi.apiGetEducationCount
import com.ssjm.sw_hackathon.educationApi.openApi.apiGetEducationInfo
import com.ssjm.sw_hackathon.goalApi.apiGetDailyTasks
import com.ssjm.sw_hackathon.goalApi.apiGetProgressGoal
import com.ssjm.sw_hackathon.goalApi.getDailyTasks.GetDailyTask
import com.ssjm.sw_hackathon.goalApi.getProgressGoal.GetProgressGoalResult
import com.ssjm.sw_hackathon.home.recycler.HomeTodoAdapter
import com.ssjm.sw_hackathon.home.recycler.HomeTodoItem
import com.ssjm.sw_hackathon.token.GloabalApplication
import java.time.LocalDate

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

    // 커버 이미지
    private var coverImages: MutableList<String> = mutableListOf("note1", "barista2", "note2", "barista1")

    private var bookmarkList: MutableList<GetBookmarks>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        // 사용자 이름 조회
        apiGetUserName(
            setUserName = {
                setUserName(it)
            }
        )

        // 목표 조회 -> 오늘의 할 일 조회
        apiGetProgressGoal(
            setGoalInfo = {
                setDailyTasks(it)
            }
        )

        // 북마크 교육 조회
        apiGetBookmark(
            getBookmark = {
                getBookmark(it)
            }
        )

        // 모두 보기
        binding.textShowAllEdu.setOnClickListener(View.OnClickListener {
            view.findNavController().navigate(R.id.action_menu_home_to_education)
        })

        // 찜하러 가기
        binding.textNoneBookmarkBtn.setOnClickListener(View.OnClickListener {
            view.findNavController().navigate(R.id.action_menu_home_to_education)
        })
    }

    // 이름 세팅
    private fun setUserName(userName: String) {
        binding.textName.text = userName
        binding.textName2.text = userName
    }

    // 오늘의 할 일 세팅
    private fun setDailyTasks(goalInfo: GetProgressGoalResult) {
        binding.textJob.text = goalInfo.name

        apiGetDailyTasks(
            goalInfo.id,
            LocalDate.now(),
            setDailyTask = {
                setDailyTasks(it)
            }
        )
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

    // 오늘의 할 일 받아와서 세팅
    private fun setDailyTasks(tasks: MutableList<GetDailyTask>) {
        binding.textTodoCount.text = "(" + tasks.size.toString() + ")"

        for(i: Int in 0..(tasks.size - 1)) {
            addTodo(
                HomeTodoItem(
                    coverImages[i],
                    tasks[i].name,
                    "1일째 실천중",
                    tasks[i].days
                )
            )
        }
    }

    private fun getBookmark(bookmarks: MutableList<GetBookmarks>?) {
        // 찜한 목록이 없는 경우
        if (bookmarks == null || bookmarks.size == 0) {
            binding.textShowAllEdu.visibility = View.GONE
            binding.recyclerviewEduBookmark.visibility = View.GONE

            binding.linearNoneBookmark.visibility = View.VISIBLE
        }
        else {
            binding.textShowAllEdu.visibility = View.VISIBLE
            binding.recyclerviewEduBookmark.visibility = View.VISIBLE

            binding.linearNoneBookmark.visibility = View.GONE

            bookmarkList = bookmarks

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
        if(educationItems != null && bookmarkList != null) {
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