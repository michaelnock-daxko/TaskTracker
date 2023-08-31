package mi.xi.timer.ui.screens.home.screens.task_create

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import mi.xi.timer.R
import mi.xi.timer.ui.components.CenterColumn
import mi.xi.timer.ui.components.OneLineTextField
import mi.xi.timer.ui.components.TimerScaffold
import mi.xi.timer.ui.screens.home.screens.task_track.TimerDialog

@Composable
fun TaskCreateScreen(
    onBackPressed: () -> Unit,
    onTaskCreated: () -> Unit,
    viewModel: TaskCreateViewModel = hiltViewModel()
) {
    if (viewModel.isTaskAdded) onTaskCreated()

    TimerScaffold(title = "Create Task", onBackPressed = onBackPressed) {
        CenterColumn {
            OneLineTextField(
                value = viewModel.taskName,
                label = stringResource(R.string.task_name),
                onValueChange = viewModel::updateTaskName
            )
            Button(onClick = viewModel::createTask) {
                Text(text = stringResource(R.string.create_task))
            }
        }
    }
    if (viewModel.errors.isNotBlank()) {
        TimerDialog(
            titleResource = R.string.error,
            onConfirm = viewModel::clearErrors,
            onCancel = viewModel::clearErrors
        ) {
            Text(text = viewModel.errors)
        }
    }
}
