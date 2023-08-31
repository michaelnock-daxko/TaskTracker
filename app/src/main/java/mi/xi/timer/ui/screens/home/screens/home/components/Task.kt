package mi.xi.timer.ui.screens.home.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import mi.xi.timer.R
import mi.xi.timer.data.entities.Task
import mi.xi.timer.ui.theme.MarginSmall

@Composable
fun Task(
    task: Task,
    onEditClicked: (Task) -> Unit,
    onDeleteClicked: (Task) -> Unit,
    onClicked: (Task) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClicked(task) }
        .padding(all = MarginSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.weight(1f), text = task.name)
        Button(onClick = { onEditClicked(task) }) { Text(text = stringResource(R.string.edit)) }
        Button(onClick = { onDeleteClicked(task) }) { Text(text = stringResource(R.string.delete)) }
    }
}