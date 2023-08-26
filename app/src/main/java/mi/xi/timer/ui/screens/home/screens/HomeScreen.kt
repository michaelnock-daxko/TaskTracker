package mi.xi.timer.ui.screens.home.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    Column {
        Text(text = "You are home")
        Button(onClick = viewModel::logOut) {
            Text(text = "Log Out")
        }
    }
}
