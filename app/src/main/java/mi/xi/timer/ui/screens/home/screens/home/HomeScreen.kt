package mi.xi.timer.ui.screens.home.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import mi.xi.timer.R
import mi.xi.timer.data.entities.Task
import mi.xi.timer.ui.components.OneLineTextField
import mi.xi.timer.ui.components.TimerScaffold
import mi.xi.timer.ui.screens.home.screens.home.components.Task
import mi.xi.timer.ui.screens.home.screens.task_track.TimerDialog
import mi.xi.timer.ui.theme.MarginMedium

@Composable
fun HomeScreen(
    onTaskClicked: (Task) -> Unit,
    onAddTaskClicked: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var taskToDelete by remember { mutableStateOf<Task?>(null) }
    var taskToEdit by remember { mutableStateOf<Task?>(null) }
    TimerScaffold(title = stringResource(R.string.task_tracker)) {
        Column {
            Text(text = stringResource(R.string.your_current_tasks))
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(viewModel.tasks) { task ->
                    Task(
                        task = task,
                        onClicked = onTaskClicked,
                        onEditClicked = { taskToEdit = it },
                        onDeleteClicked = { taskToDelete = it }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MarginMedium),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(onClick = onAddTaskClicked) {
                    Text(text = stringResource(R.string.add_task))
                }
                Button(onClick = viewModel::logOut) {
                    Text(text = stringResource(R.string.log_out))
                }
            }
        }
    }
    taskToDelete?.let { task ->
        TimerDialog(
            titleResource = R.string.confirm_deletion,
            onConfirm = {
                viewModel.deleteTask(task)
                taskToDelete = null
            },
            onCancel = { taskToDelete = null }
        ) {
            Text(text = stringResource(R.string.confirm_event_deletion))
        }
    }
    taskToEdit?.let { task ->
        var name by remember(task) { mutableStateOf(task.name) }
        EditDialog(
            text = name,
            onConfirm = {
                viewModel.updateTask(task.copy(name = name))
                taskToEdit = null
            },
            onCancel = { taskToEdit = null },
            onUpdate = { name = it }
        )
    }
}

@Composable
fun EditDialog(
    text: String,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    onUpdate: (String) -> Unit
) {
    TimerDialog(
        titleResource = R.string.task_name,
        onConfirm = onConfirm,
        onCancel = onCancel
    ) {
        OneLineTextField(
            value = text,
            label = stringResource(R.string.name),
            onValueChange = onUpdate
        )
    }
}
