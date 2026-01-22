package com.example.kotlinperusteet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.example.kotlinperusteet.domain.mockTasks
import com.example.kotlinperusteet.domain.Task
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class TaskViewModel : ViewModel() {

    var taskList by mutableStateOf(listOf<Task>())
        private set

    init{
        taskList = mockTasks
    }

    fun addTask(task: Task){
        taskList = taskList + task
    }

    fun toggleDone(id: Int){
        taskList = taskList.map{
            if (it.id == id) it.copy (done = !it.done) else it
        }
    }

    fun filterByDone(done: Boolean?){
        taskList = taskList.filter { it.done == done }
    }

    fun sortByDueDate() {
        taskList = taskList.sortedBy { it.dueDate }
    }

    fun deleteTask(id: Int) {
        taskList = taskList.filter{ it.id != id }
    }

    fun reset(){
        taskList = mockTasks
    }
}