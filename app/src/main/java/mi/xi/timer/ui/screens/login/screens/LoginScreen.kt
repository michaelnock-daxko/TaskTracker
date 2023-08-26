@file:OptIn(ExperimentalMaterial3Api::class)

package mi.xi.timer.ui.screens.login.screens

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import mi.xi.timer.ui.components.CenterColumn
import mi.xi.timer.ui.components.ErrorText

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onSignupClick: () -> Unit
) {
    CenterColumn {
        Text(text = "Log In")
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
            ErrorText(text = viewModel.error)
        }
        Button(onClick = viewModel::login) {
            Text(text = "Login")
        }
        OutlinedButton(onClick = onSignupClick) {
            Text(text = "Sign Up")
        }
    }
}