package com.ssjm.sw_hackathon.onBoarding.third

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ssjm.sw_hackathon.R
import com.ssjm.sw_hackathon.databinding.FragmentThirdOnBoardBinding
import com.ssjm.sw_hackathon.onBoarding.OnBoardingActivity
import com.ssjm.sw_hackathon.onBoarding.end.EndOnBoardFragment
import com.ssjm.sw_hackathon.onBoardingApi.addGoal.AddGoalRequest
import com.ssjm.sw_hackathon.onBoardingApi.addGoal.AddGoalTasks
import com.ssjm.sw_hackathon.onBoardingApi.apiAddGoal
import com.ssjm.sw_hackathon.token.GloabalApplication


class ThirdOnBoardFragment : Fragment() {
    // ViewBinding Setting
    private var _binding: FragmentThirdOnBoardBinding? = null
    private val binding get() = _binding!!

    // 프래그먼트 전환을 위해
    private var activity: OnBoardingActivity? = null

    // 요일별 선택 정보
    private var days = mutableListOf<Boolean>(false, false, false, false, false, false, false)
    private var daysName = mutableListOf<String>("월", "화", "수", "목", "금", "토", "일")
    private var checked = false

    // 목표
    private var goal: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = getActivity() as OnBoardingActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdOnBoardBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 목표
        goal = arguments?.getString("goal")

        // 이전으로
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            requireActivity().supportFragmentManager.popBackStack()
        }

        // 요일 선택
        binding.editOnboardThird2.setOnClickListener(View.OnClickListener {
            if(binding.linearSelectDay.visibility == View.GONE) {
                binding.linearSelectDay.visibility = View.VISIBLE
            }
            else {
                binding.linearSelectDay.visibility = View.GONE
            }
        })

        // 배경 터치 시 요일 선택창 닫기
        /*binding.fragmentBackground.setOnClickListener(View.OnClickListener {
            if(binding.linearSelectDay.visibility == View.VISIBLE) {
                binding.linearSelectDay.visibility = View.GONE
            }
        })*/

        // 월요일 선택
        binding.textMonday.setOnClickListener(View.OnClickListener {
            if(days[0] === false) {
                days[0] = true

                binding.textMonday.setBackgroundResource(R.drawable.shape_select_day_selected)
                binding.textMonday.setTextColor(resources.getColor(R.color.white))
            }
            else {
                days[0] = false

                binding.textMonday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                binding.textMonday.setTextColor(resources.getColor(R.color.main_color_1))
            }
            checkSelected()
        })

        // 화요일 선택
        binding.textTuesday.setOnClickListener(View.OnClickListener {
            if(days[1] === false) {
                days[1] = true

                binding.textTuesday.setBackgroundResource(R.drawable.shape_select_day_selected)
                binding.textTuesday.setTextColor(resources.getColor(R.color.white))
            }
            else {
                days[1] = false

                binding.textTuesday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                binding.textTuesday.setTextColor(resources.getColor(R.color.main_color_1))
            }
            checkSelected()
        })

        // 수요일 선택
        binding.textWednesday.setOnClickListener(View.OnClickListener {
            if(days[2] === false) {
                days[2] = true

                binding.textWednesday.setBackgroundResource(R.drawable.shape_select_day_selected)
                binding.textWednesday.setTextColor(resources.getColor(R.color.white))
            }
            else {
                days[2] = false

                binding.textWednesday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                binding.textWednesday.setTextColor(resources.getColor(R.color.main_color_1))
            }
            checkSelected()
        })

        // 목요일 선택
        binding.textThursday.setOnClickListener(View.OnClickListener {
            if(days[3] === false) {
                days[3] = true

                binding.textThursday.setBackgroundResource(R.drawable.shape_select_day_selected)
                binding.textThursday.setTextColor(resources.getColor(R.color.white))
            }
            else {
                days[3] = false

                binding.textThursday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                binding.textThursday.setTextColor(resources.getColor(R.color.main_color_1))
            }
            checkSelected()
        })

        // 금요일 선택
        binding.textFriday.setOnClickListener(View.OnClickListener {
            if(days[4] === false) {
                days[4] = true

                binding.textFriday.setBackgroundResource(R.drawable.shape_select_day_selected)
                binding.textFriday.setTextColor(resources.getColor(R.color.white))
            }
            else {
                days[4] = false

                binding.textFriday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                binding.textFriday.setTextColor(resources.getColor(R.color.main_color_1))
            }
            checkSelected()
        })

        // 토요일 선택
        binding.textSaturday.setOnClickListener(View.OnClickListener {
            if(days[5] === false) {
                days[5] = true

                binding.textSaturday.setBackgroundResource(R.drawable.shape_select_day_selected)
                binding.textSaturday.setTextColor(resources.getColor(R.color.white))
            }
            else {
                days[5] = false

                binding.textSaturday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                binding.textSaturday.setTextColor(resources.getColor(R.color.main_color_1))
            }
            checkSelected()
        })

        // 일요일 선택
        binding.textSunday.setOnClickListener(View.OnClickListener {
            if(days[6] === false) {
                days[6] = true

                binding.textSunday.setBackgroundResource(R.drawable.shape_select_day_selected)
                binding.textSunday.setTextColor(resources.getColor(R.color.white))
            }
            else {
                days[6] = false

                binding.textSunday.setBackgroundResource(R.drawable.shape_select_day_unselected)
                binding.textSunday.setTextColor(resources.getColor(R.color.main_color_1))
            }
            checkSelected()
        })

        // 요일 선택 완료
        binding.linearDoneSelectDay.setOnClickListener(View.OnClickListener {
            if(checked) {
                var dayText: String = ""
                for(i: Int in 0..6) {
                    if(days[i]) {
                        if(dayText == "")
                            dayText += daysName[i]
                        else
                            dayText += ", " + daysName[i]
                    }
                }

                binding.editOnboardThird2.setText(dayText)
                binding.linearSelectDay.visibility = View.GONE
            }
            else {
                Toast.makeText(requireContext(), getString(R.string.toast_unselect_day), Toast.LENGTH_SHORT).show()
            }
        })

        // 완료 버튼
        binding.linearDoneOnboard.setOnClickListener(View.OnClickListener {
            var todo: String = binding.editOnboardThird1.text.toString()
            var dayText: String = binding.editOnboardThird2.text.toString()
            if(todo == "" || todo == null) {
                Toast.makeText(requireContext(), getString(R.string.toast_unselect_todo), Toast.LENGTH_SHORT).show()
            }
            else if(dayText == "" || dayText == null) {
                Toast.makeText(requireContext(), getString(R.string.toast_unselect_day), Toast.LENGTH_SHORT).show()
            }
            else {
                var selectDays: MutableList<String> = mutableListOf()
                for(i: Int in 0..6) {
                    if(days[i]) {
                        selectDays.add(daysName[i])
                    }
                }

                apiAddGoal(
                    AddGoalRequest(
                        tasks = mutableListOf(
                            AddGoalTasks(
                                name = todo,
                                days = selectDays
                            )
                        ),
                        name = goal!!
                    ),
                    getGoalId = {
                        saveGoalAndTask(it)
                    }
                )
            }
        })
    }

    private fun checkSelected() {
        checked = false
        for(i: Int in 0..6) {
            if(days[i]) {
                checked = true
                break
            }
        }

        if(!checked) {
            binding.linearDoneSelectDay.setBackgroundResource(R.drawable.shape_unselected)
        }
        else {
            binding.linearDoneSelectDay.setBackgroundResource(R.drawable.shape_selected)
        }
    }

    private fun saveGoalAndTask(goalId: Int) {
        Log.d("Test", "----------------------------------------------------------")

        GloabalApplication.prefs.setInt("goalId", goalId)

        activity?.setFragment(EndOnBoardFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}