@file:OptIn(ExperimentalMaterial3Api::class)

package mi.xi.timer.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import mi.xi.timer.ui.theme.ColorError

@Composable
fun ErrorText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = ColorError
    )
}

@Composable
fun OneLineTextField(value: String, label: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        label = { Text(text = label) },
        onValueChange = onValueChange,
        maxLines = 1,
        singleLine = true
    )
}