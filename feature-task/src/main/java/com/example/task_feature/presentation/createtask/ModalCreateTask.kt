package com.example.task_feature.presentation.createtask

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.core_models.domain.Task
import com.example.core_models.domain.TaskCategory
import com.example.core_navigation.NavEvent
import com.example.task_feature.presentation.components.DateTimePeeker
import com.example.task_feature.presentation.components.SimpleTextField
import com.example.task_feature.util.toDate
import java.time.Instant

class ModalCreateTaskCardKey(val onTaskCreated : (()->Unit)? = null)

@Composable
fun ModalCreateTask(
    onTaskCreated: (() -> Unit)? = null,
    onNavigationEvent : (navEvent : NavEvent) -> Unit
){
    val viewModel : ModalCreateTaskViewModel = hiltViewModel<ModalCreateTaskViewModel>()
    val taskState by viewModel.taskState.collectAsState()
    ModalCreateTaskView(
        onTaskCreated = onTaskCreated,
        onNavigationEvent = onNavigationEvent,
        onEvent = { event->
            viewModel.handleEvent(event = event)
        },
        taskState = taskState
    )
}

@Composable
fun ModalCreateTaskView(
    onTaskCreated: (() -> Unit)? = null,
    onNavigationEvent: (navEvent: NavEvent) -> Unit = {},
    onEvent : (event : CreateTaskEvent) -> Unit = {},
    taskState : Task = Task(0, "", "", null, TaskCategory.UNSPECIFIED)
){
    var showMenu by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    Box{
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 12.dp),
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Новое задание",
                        fontSize = 20.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(8.dp)
                ){
                    Column(modifier = Modifier.fillMaxWidth()){
                        SimpleTextField(
                            hint = "Введите название задания:",
                            value = taskState.taskName,
                            onValueChange = { newValue->
                                onEvent(CreateTaskEvent.ChangeTaskState(
                                    newTaskInfo = taskState.copy(taskName = newValue)
                                ))
                            },
                            modifier = Modifier.padding(top = 12.dp, start = 4.dp)
                        )
                        HorizontalDivider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 12.dp, start = 4.dp)
                                .clickable(onClick = {
                                    showTimePicker = true
                                }),
                            text = "Дедлайн: " + (taskState.deadline?.toEpochMilli()?.toDate() ?: "не указан")
                        )
                        HorizontalDivider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Box{
                            Text(
                                modifier = Modifier
                                    .padding(top = 12.dp, start = 4.dp)
                                    .clickable { showMenu = true },
                                text = "Категория: ${taskState.category}"
                            )
                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = {showMenu = false}
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Срочное") },
                                    onClick = {
                                        onEvent(
                                            CreateTaskEvent.ChangeTaskState(
                                                taskState.copy(category = TaskCategory.URGENT
                                                )))
                                        showMenu = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Несрочное") },
                                    onClick = {
                                        onEvent(
                                            CreateTaskEvent.ChangeTaskState(
                                                taskState.copy(category = TaskCategory.NONURGENT
                                                )))
                                        showMenu = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Продолжительное") },
                                    onClick = {
                                        onEvent(
                                            CreateTaskEvent.ChangeTaskState(
                                                taskState.copy(category = TaskCategory.LONGTIME
                                                )))
                                        showMenu = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Повторяющееся") },
                                    onClick = {
                                        onEvent(
                                            CreateTaskEvent.ChangeTaskState(
                                                taskState.copy(category = TaskCategory.REPEATABLE
                                                )))
                                        showMenu = false
                                    }
                                )
                            }
                        }

                        HorizontalDivider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = taskState.taskDescription,
                            onValueChange = { newDescription->
                                onEvent(CreateTaskEvent.ChangeTaskState(
                                    newTaskInfo = taskState.copy(taskDescription = newDescription)
                                ))
                            },
                            label = {
                                Text("Описание задания")
                            },
                            placeholder = {
                                Text("Описание задания")
                            }
                        )
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        onEvent(CreateTaskEvent.CreateTask(
                            onTaskCreated = {
                            onTaskCreated?.invoke()
                            onNavigationEvent(NavEvent.NavBack)
                            }
                        ))

                    }) { Text("Создать") }
                }
            }
        }
        if (showTimePicker){
            DateTimePeeker(
                selectedTimeMills = if (taskState.deadline!=null) Instant.ofEpochMilli(taskState.deadline!!.toEpochMilli()) else null
            ) { selectedDeadlineMills->
                onEvent(CreateTaskEvent.ChangeTaskState(newTaskInfo = taskState.copy(deadline = Instant.ofEpochMilli(selectedDeadlineMills))))
                showTimePicker = false
            }
        }
    }
}

@Composable
@Preview
fun ModalEditTaskPreview(){
    ModalCreateTaskView()
}