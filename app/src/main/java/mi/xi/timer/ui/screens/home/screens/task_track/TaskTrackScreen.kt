package mi.xi.timer.ui.screens.home.screens.task_track

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import mi.xi.timer.R
import mi.xi.timer.ui.components.TimerScaffold
import mi.xi.timer.ui.theme.MarginMedium
import mi.xi.timer.util.timeHours
import mi.xi.timer.util.timeMillis
import mi.xi.timer.util.timeMinutes
import mi.xi.timer.util.timeSeconds

@Composable
fun TaskTrackScreen(
    taskId: Long,
    onBackPressed: () -> Unit,
    viewModel: TaskTrackViewModel = hiltViewModel()
) {
    LaunchedEffect(taskId) {
        viewModel.fetchTask(taskId)
        viewModel.startTimer()
    }
    val isPaused by viewModel.isPaused.collectAsState()
    var isConfirmDialogOpen by remember { mutableStateOf(false) }

    val task = viewModel.task
    val title = if (task != null) {
        stringResource(R.string.new_event_for, task.name)
    } else {
        stringResource(id = R.string.loading)
    }

    TimerScaffold(title = title, onBackPressed = onBackPressed) {
        if (task == null) {
            Text(text = stringResource(R.string.loading))
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Timer(millis = viewModel.elapsedMillis)
                PauseButton(isPaused = isPaused, onClick = viewModel::togglePause)
                SaveButton(onClick = {
                    viewModel.pauseTimer()
                    isConfirmDialogOpen = true
                })
            }
        }
    }
    if (isConfirmDialogOpen) {
        TimerDialog(
            titleResource = R.string.finish_entry,
            onConfirm = {
                viewModel.saveEntry()
                onBackPressed()
            },
            onCancel = { isConfirmDialogOpen = false }) {

        }
    }
}

@Composable
fun TimerDialog(
    @StringRes titleResource: Int,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    contents: @Composable ColumnScope.() -> Unit
) {
    AlertDialog(
        containerColor = Color.White,
        title = {
            Text(
                text = stringResource(id = titleResource),
                style = MaterialTheme.typography.headlineMedium
            )
        },
        text = { Column { contents() } },
        confirmButton = {
            Button(
                onClick = { onConfirm() },
            ) {
                Text(text = stringResource(R.string.button_ok))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onCancel,
            ) {
                Text(text = stringResource(R.string.button_cancel))
            }
        },
        onDismissRequest = { onCancel() }
    )
}

@Composable
fun PauseButton(isPaused: Boolean, onClick: () -> Unit) {
    val textRes = if (isPaused) R.string.resume else R.string.pause
    Button(onClick = onClick) { Text(text = stringResource(id = textRes)) }
}

@Composable
fun SaveButton(onClick: () -> Unit) {
    Button(onClick = onClick) { Text(text = stringResource(R.string.save_result)) }
}

@Composable
fun Timer(millis: Long) {
    val hours = millis.timeHours()
    Row(modifier = Modifier.padding(MarginMedium)) {
        if (hours > 0) TimerText(number = hours, width = 100)
        TimerText(number = millis.timeMinutes())
        TimerText(number = millis.timeSeconds())
        TimerText(number = millis.timeMillis(), width = 2)
    }
}

@Composable
fun TimerText(number: Long, width: Int = 3) {
    Text(
        text = "$number:".padStart(3, '0').take(width),
        style = MaterialTheme.typography.headlineLarge
    )
}