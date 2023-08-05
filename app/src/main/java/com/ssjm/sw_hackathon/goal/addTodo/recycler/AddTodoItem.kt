package com.ssjm.sw_hackathon.goal.addTodo.recycler

data class AddTodoItem (
    var todoContent: String? = null,
    var days: MutableList<Boolean> = mutableListOf<Boolean>(false, false, false, false, false, false, false),
    var checked: Boolean = false
)