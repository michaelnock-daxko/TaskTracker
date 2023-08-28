package mi.xi.timer.ui.screens.home.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import mi.xi.timer.data.entities.Task
import mi.xi.timer.ui.screens.home.screens.home.components.Task

@Composable
fun HomeScreen(
    onTaskClicked: (Task) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column {
        Text(text = "You are home")
        LazyColumn {
            items(viewModel.tasks) { task -> Task(task = task, onClicked = onTaskClicked) }
        }
        Button(onClick = viewModel::addTask) {
            Text(text = "Add task")
        }
        Button(onClick = viewModel::logOut) {
            Text(text = "Log Out")
        }
    }
}
