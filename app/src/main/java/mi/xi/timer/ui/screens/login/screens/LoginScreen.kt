@file:OptIn(ExperimentalMaterial3Api::class)

package mi.xi.timer.ui.screens.login.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import mi.xi.timer.ui.theme.ColorError
import mi.xi.timer.ui.theme.MarginMedium

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onSignupClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.username,
            label = { Text(text = "Username") },
            onValueChange = viewModel::updateUsername
        )
        OutlinedTextField(
            value = viewModel.password,
            label = { Text(text = "Password") },
            onValueChange = viewModel::updatePassword
        )
        if (viewModel.error.isNotEmpty()) {
            Text(
                text = viewModel.error,
                style = MaterialTheme.typography.labelSmall,
                color = ColorError
            )
        }
        Row(modifier = Modifier.padding(top = MarginMedium)) {
            Button(onClick = viewModel::login) {
                Text(text = "Login")
            }
            Button(onClick = onSignupClick) {
                Text(text = "Sign Up")
            }
        }
    }
}