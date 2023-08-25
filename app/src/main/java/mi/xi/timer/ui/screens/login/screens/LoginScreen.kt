@file:OptIn(ExperimentalMaterial3Api::class)

package mi.xi.timer.ui.screens.login.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column {
        OutlinedTextField(
            value = "username",
            label = { Text(text = "Username")},
            onValueChange = {}
        )
        OutlinedTextField(
            value = "password",
            label = { Text(text = "Username")},
            onValueChange = {}
        )
    }
}