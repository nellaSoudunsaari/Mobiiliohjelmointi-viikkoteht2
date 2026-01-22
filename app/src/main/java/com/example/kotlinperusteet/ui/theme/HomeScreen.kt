package com.example.kotlinperusteet.ui.theme

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinperusteet.domain.Task
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinperusteet.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen(taskViewModel: TaskViewModel = viewModel()) {
    var taskTitle by remember { mutableStateOf("") }
    var taskDesc by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("ToDo-list") }
            )
        }
    )
    {   innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = {
                        taskViewModel.sortByDueDate()
                    }) {
                    Text(
                        fontSize = 12.sp,
                        text = "Sort By Due Date"
                    )
                }
                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = {
                        taskViewModel.filterByDone(true)
                }) {
                    Text("Filter by done")
                }
                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = {
                        taskViewModel.reset()
                }) {
                    Text("Reset")
                }
            }


            LazyColumn() {
                items(taskViewModel.taskList){ task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(8.dp),
                                color = Purpl
                            )
                    ) {
                        TasksRow(
                            task = task,
                            onToggle = {taskViewModel.toggleDone(task.id)},
                            onDelete = {taskViewModel.deleteTask(task.id)}
                        )
                    }
                }
            }

            OutlinedTextField(
                modifier = Modifier.padding(8.dp),
                value = taskTitle,
                onValueChange = { taskTitle = it },
                label = { Text("Enter title") }
            )

            OutlinedTextField(
                modifier = Modifier.padding(8.dp),
                value = taskDesc,
                onValueChange = { taskDesc = it },
                label = { Text("Enter description") }
            )

            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    if(taskTitle.isNotEmpty() && taskDesc.isNotEmpty()){
                        val newTaskId = (taskViewModel.taskList.maxOfOrNull { it.id } ?: 0)+1
                        val curDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

                        taskViewModel.addTask(
                            Task(
                                id = newTaskId,
                                title = taskTitle,
                                description = taskDesc,
                                priority = 1,
                                dueDate = curDate,
                                done = false
                            )
                        )

                        taskTitle = ""
                        taskDesc = ""
                    }
                }) {
                Text(text = "Add task")
            }


        }
    }
}

@Composable
fun TasksRow(
    task: Task,
    onToggle: () -> Unit,
    onDelete: () -> Unit
){
    Row(){
        Row(){
            Checkbox(
                checked = task.done,
                onCheckedChange = {onToggle()}
            )
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = task.title)
                Text(text = task.description)
                Text("Due by: ${task.dueDate}")
            }
        }
        Button( modifier = Modifier.padding(4.dp), onClick = onDelete ) {
                Text("Delete task")
            }
    }
}