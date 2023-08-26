package mi.xi.timer.ui.components

import androidx.compose.material3.MaterialTheme
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
