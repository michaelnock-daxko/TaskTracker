package mi.xi.timer.ui.screens.home.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import mi.xi.timer.R
import mi.xi.timer.data.entities.Task
import mi.xi.timer.ui.components.TimerScaffold
import mi.xi.timer.ui.screens.home.screens.home.components.Task

@Composable
fun HomeScreen(
    onTaskClicked: (Task) -> Unit,
    onAddTaskClicked: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    TimerScaffold(title = stringResource(R.string.task_tracker)) {
        Column {
            Text(text = stringResource(R.string.your_current_tasks))
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(viewModel.tasks) { task -> Task(task = task, onClicked = onTaskClicked) }
            }
            Button(onClick = onAddTaskClicked) {
                Text(text = stringResource(R.string.add_task))
            }
            Button(onClick = viewModel::logOut) {
                Text(text = stringResource(R.string.log_out))
            }
        }
    }
}
