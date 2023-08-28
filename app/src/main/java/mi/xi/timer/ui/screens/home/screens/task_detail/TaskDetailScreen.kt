package mi.xi.timer.ui.screens.home.screens.task_detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TaskDetailScreen(taskId: Long, viewModel: TaskDetailViewModel = hiltViewModel()) {
    LaunchedEffect(taskId) {
        viewModel.fetchTask(taskId)
    }
    val task = viewModel.task
    if (task != null) {
        Text(text = task.name)
    } else {
        Text(text = "Loading")
    }
}