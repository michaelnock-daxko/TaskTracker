package mi.xi.timer.ui.screens.home.screens.task_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import mi.xi.timer.R
import mi.xi.timer.data.entities.TaskEvent
import mi.xi.timer.ui.components.TimerScaffold
import mi.xi.timer.util.toTimeString

@Composable
fun TaskDetailScreen(
    taskId: Long,
    onBackPressed: () -> Unit,
    onTrackEvent: () -> Unit,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(taskId) { viewModel.fetchTask(taskId) }
    val task = viewModel.task
    TimerScaffold(title = task?.name ?: "", onBackPressed = onBackPressed) {
        if (task != null) {
            TaskDetails(events = viewModel.taskEvents, onTrackEvent = onTrackEvent)
        } else {
            Text(text = stringResource(R.string.loading))
        }
    }
}

@Composable
fun TaskDetails(
    events: List<TaskEvent>,
    onTrackEvent: () -> Unit
) {
    Column {
        LazyColumn {
            items(events) { Text(text = "${it.id} - ${it.millis.toTimeString()}") }
        }
        Button(onClick = onTrackEvent) { Text(text = "Track Event") }
    }
}