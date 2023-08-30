package mi.xi.timer.ui.screens.home.screens.task_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import mi.xi.timer.R
import mi.xi.timer.data.entities.TaskEvent
import mi.xi.timer.ui.components.TimerScaffold
import mi.xi.timer.ui.screens.home.screens.task_track.TimerDialog
import mi.xi.timer.ui.theme.MarginSmall
import mi.xi.timer.util.toTimeString
import java.text.SimpleDateFormat

@Composable
fun TaskDetailScreen(
    taskId: Long,
    onBackPressed: () -> Unit,
    onTrackEvent: () -> Unit,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(taskId) { viewModel.fetchTask(taskId) }
    val task = viewModel.task
    var selectedEvent by remember { mutableStateOf<TaskEvent?>(null) }
    val event = selectedEvent
    TimerScaffold(title = task?.name ?: "", onBackPressed = onBackPressed) {
        if (task != null) {
            TaskDetails(
                events = viewModel.taskEvents,
                onDeleteEvent = { selectedEvent = it },
                onTrackEvent = onTrackEvent
            )
        } else {
            Text(text = stringResource(R.string.loading))
        }
        if (event != null) {
            TimerDialog(
                titleResource = R.string.confirm_deletion,
                onConfirm = {
                    viewModel.deleteTaskEvent(event)
                    selectedEvent = null
                },
                onCancel = { selectedEvent = null }
            ) {
                Text(text = stringResource(R.string.confirm_event_deletion))
            }
        }
    }
}

@Composable
fun TaskDetails(
    events: List<TaskEvent>,
    onDeleteEvent: (TaskEvent) -> Unit,
    onTrackEvent: () -> Unit
) {
    Column {
        LazyColumn {
            items(events) { TaskEvent(taskEvent = it, onDeleteEvent = onDeleteEvent) }
        }
        Button(onClick = onTrackEvent) { Text(text = "Track Event") }
    }
}

@Composable
fun TaskEvent(taskEvent: TaskEvent, onDeleteEvent: (TaskEvent) -> Unit) {
    val dateTime = SimpleDateFormat.getDateTimeInstance().format(taskEvent.creationDate)

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.padding(all = MarginSmall)) {
            Text(
                text = taskEvent.millis.toTimeString(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = dateTime, style = MaterialTheme.typography.labelSmall)
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { onDeleteEvent(taskEvent) }) {
            Text(text = "Delete")
        }
    }
}