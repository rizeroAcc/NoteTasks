package com.example.task_feature.presentation.edittask

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
import com.example.core_navigation.NavEvent
import com.example.task_feature.domain.Task
import com.example.task_feature.domain.TaskCategory

data class ModalEditTaskCardKey(
    val taskID : Long
)

@Composable
fun ModalEditTask(
    taskID : Long,
    onNavigationEvent : (navEvent : NavEvent) -> Unit
){
    val viewModel : ModalEditTaskViewModel = hiltViewModel<ModalEditTaskViewModel, ModalEditTaskViewModel.Factory>(
        creationCallback = { factory ->
            factory.create(taskID = taskID)
        }
    )
    ModalEditTaskView(
        onNavigationEvent = onNavigationEvent,
        onEvent = { event->
            viewModel.handleEvent(event = event)
        },
    )

}

@Composable
fun ModalEditTaskView(
    onNavigationEvent: (navEvent: NavEvent) -> Unit = {},
    onEvent : (event : ModalEditTaskEvent) -> Unit = {},
    taskState : Task = Task(0,"","",null,TaskCategory.UNSPECIFIED)
){
    var showMenu by remember { mutableStateOf(false) }
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
                    text = taskState.taskName,
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
                    if (taskState.deadline != null){
                        Text(
                            modifier = Modifier.padding(top = 12.dp, start = 4.dp),
                            text = "Дедлайн: $taskState.deadline"
                        )
                        HorizontalDivider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
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
                                        ModalEditTaskEvent.ChangeTaskState(
                                        taskState.copy(category = TaskCategory.NONURGENT
                                        )))
                                    showMenu = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Несрочное") },
                                onClick = {
                                    onEvent(
                                        ModalEditTaskEvent.ChangeTaskState(
                                        taskState.copy(category = TaskCategory.URGENT
                                        )))
                                    showMenu = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Долгосрочное") },
                                onClick = {
                                    onEvent(
                                        ModalEditTaskEvent.ChangeTaskState(
                                        taskState.copy(category = TaskCategory.LONGTIME
                                        )))
                                    showMenu = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Повторяющееся") },
                                onClick = {
                                    onEvent(
                                        ModalEditTaskEvent.ChangeTaskState(
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
                            onEvent(
                                ModalEditTaskEvent.ChangeTaskState(
                                taskState.copy(taskDescription = newDescription)
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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                Button(onClick = {
                    onEvent(ModalEditTaskEvent.UpdateTaskCard())
                    onNavigationEvent(NavEvent.NavBack)
                }) { Text("Сохранить") }
                Button(onClick = {
                    //todo сделать возможность завершения как не важное
                    onEvent(ModalEditTaskEvent.FinishTask(false))
                    onNavigationEvent(NavEvent.NavBack)
                }) { Text("Завершить")}
            }
        }
    }
}

@Composable
@Preview
fun ModalEditTaskPreview(){
    ModalEditTaskView()
}