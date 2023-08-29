@file:OptIn(ExperimentalMaterial3Api::class)

package mi.xi.timer.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TimerScaffold(
    title: String,
    onBackPressed: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Scaffold(topBar = { TimerTopBar(title = title, onBackPressed = onBackPressed) }) {
        Surface(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            content()
        }
    }
}

@Composable
fun TimerTopBar(title: String, onBackPressed: (() -> Unit)? = null) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (onBackPressed != null) {
                IconButton(onClick = onBackPressed) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back arrow")
                }
            }
        }
    )
}