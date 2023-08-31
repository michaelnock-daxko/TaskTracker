package mi.xi.timer.ui.screens.home.screens.task_track

import androidx.annotation.StringRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
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
                TimerAnimation(millis = viewModel.elapsedMillis, isEnabled = isPaused)
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
fun TimerAnimation(millis: Long, isEnabled: Boolean) {
    val minutes by animateFloatAsState(
        targetValue = (millis.timeMinutes()+ 1) / 60f,
        animationSpec = tween(durationMillis = 60_000, easing = LinearEasing),
        label = "color_state"
    )
    val seconds by animateFloatAsState(
        targetValue = (millis.timeSeconds() + 1) / 60f,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        label = "color_state"
    )
    val milliseconds = millis.timeMillis() / 1000f
    val alpha by animateFloatAsState(
        targetValue = if (isEnabled) 0.2f else 1f,
        animationSpec = tween(durationMillis = 200, easing = LinearEasing),
        label = "color_state"
    )

    Row {
        val modifier = Modifier
            .weight(1f)
            .alpha(alpha)
        CircleProgress(progress = minutes, modifier = modifier, label = "h")
        CircleProgress(progress = seconds, modifier = modifier, label = "m")
        CircleProgress(progress = milliseconds, modifier = modifier, label = "s")
    }
}

@Composable
fun CircleProgress(modifier: Modifier, progress: Float, label: String) {
    Box(
        modifier = modifier
            .padding(all = MarginMedium)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(progress)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Color.DarkGray)
        )
        Text(text = label.uppercase(), fontSize = 20.sp, color = Color.White)
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
        containerColor = MaterialTheme.colorScheme.primaryContainer,
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
        onDismissRequest = { onCancel() },
        textContentColor = Color.DarkGray,
        titleContentColor = Color.Black
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
    Row(modifier = Modifier.padding(MarginMedium), verticalAlignment = Alignment.CenterVertically) {
        if (hours > 0) TimerText(number = hours)
        TimerText(number = millis.timeMinutes())
        TimerText(number = millis.timeSeconds(), suffix = "")
        TimerText(number = millis.timeMillis(), fontSize = 20, suffix = "")
    }
}

@Composable
fun TimerText(number: Long, fontSize: Int = 35, suffix: String = ":") {
    val zeroPadded = "$number".padStart(2, '0').take(2)
    Text(
        text = "$zeroPadded$suffix",
        style = MaterialTheme.typography.headlineLarge,
        fontSize = fontSize.sp
    )
}