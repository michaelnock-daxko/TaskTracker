package mi.xi.timer.ui.screens.home.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mi.xi.timer.data.entities.Task
import mi.xi.timer.ui.theme.MarginMedium

@Composable
fun Task(task: Task, onClicked: (Task) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().clickable { onClicked(task) }.padding(all = MarginMedium)) {
        Text(text = "${task.id} - ${task.name}")
    }
}