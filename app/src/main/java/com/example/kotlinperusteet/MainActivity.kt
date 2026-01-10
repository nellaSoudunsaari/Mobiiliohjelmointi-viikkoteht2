package com.example.kotlinperusteet

import android.R.attr.label
import android.R.attr.value
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinperusteet.domain.Task
import com.example.kotlinperusteet.domain.addTask
import com.example.kotlinperusteet.domain.filterByDone
import com.example.kotlinperusteet.domain.mockTasks
import com.example.kotlinperusteet.domain.sortByDueDate
import com.example.kotlinperusteet.domain.toggleDone
import com.example.kotlinperusteet.ui.theme.KotlinPerusteetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomeScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen() {
    var taskList by remember { mutableStateOf(mockTasks) }
    var taskTitle by remember { mutableStateOf("title") }
    var taskDesc by remember { mutableStateOf("description") }
    var isFiltered by remember { mutableStateOf(false) }

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
                        taskList = sortByDueDate(taskList)
                    }) {
                    Text(
                        fontSize = 12.sp,
                        text = "Sort By Due Date"
                    )
                }
                Checkbox(
                    checked = isFiltered,
                    onCheckedChange = {
                        taskList = filterByDone(taskList, true)
                        if(!isFiltered){
                            isFiltered = true
                        } else {
                            isFiltered = false
                            taskList = mockTasks
                        }
                    }
                )
                Text(text = "Filter by done")
            }


            taskList.forEach { task ->
                Row(
                    modifier = Modifier
                                .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = {
                            taskList = toggleDone(taskList, task.id)
                        }
                    )
                    Column() {
                        Text(
                            text = "${task.title} - Due: ${task.dueDate}",
                            fontWeight = FontWeight.Bold,
                            textDecoration = if(task.done){
                                TextDecoration.LineThrough
                            } else {
                                null
                            }

                        )
                        Text(
                            text = task.description,
                            textDecoration = if(task.done){
                                TextDecoration.LineThrough
                            } else {
                                null
                            }
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
                val newTask = Task(
                    id = taskList.size + 1,
                    title = taskTitle,
                    description = taskDesc,
                    priority = 1,
                    dueDate = "01-02-2026",
                    done = false
                )
                taskList = addTask(taskList, newTask)
            }) {
                Text(text = "Add task")
            }


        }
    }
}