package mi.xi.timer.ui.screens.home.screens.task_detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import mi.xi.timer.R
import mi.xi.timer.ui.components.TimerScaffold

@Composable
fun TaskDetailScreen(
    taskId: Long,
    onBackPressed: () -> Unit,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(taskId) {
        viewModel.fetchTask(taskId)
    }
    val task = viewModel.task
    TimerScaffold(title = task?.name ?: "", onBackPressed = onBackPressed) {
        if (task != null) {
            Text(text = task.name)
        } else {
            Text(text = stringResource(R.string.loading))
        }
    }
}