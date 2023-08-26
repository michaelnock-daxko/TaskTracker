@file:OptIn(ExperimentalMaterial3Api::class)

package mi.xi.timer.ui.screens.signup.screens

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import mi.xi.timer.ui.components.CenterColumn
import mi.xi.timer.ui.components.ErrorText

@Composable
fun SignupMainScreen(
    viewModel: SignupMainViewModel = hiltViewModel(),
    onUsernameCreated: (String) -> Unit,
    onLoginClicked: () -> Unit
) {
    LaunchedEffect(viewModel) {
        viewModel.isUsernameReady.collect { isReady ->
            if (isReady) onUsernameCreated(viewModel.username)
        }
    }
    CenterColumn {
        Text(text = "Sign Up")
        OutlinedTextField(
            value = viewModel.username,
            label = { Text(text = "Username") },
            onValueChange = viewModel::updateUsername,
            maxLines = 1,
            singleLine = true
        )
        if (viewModel.error.isNotEmpty()) {
            ErrorText(text = viewModel.error)
        }
        Button(onClick = viewModel::checkUsername) {
            Text(text = "Next")
        }
        OutlinedButton(onClick = onLoginClicked) {
            Text(text = "Log in")
        }
    }
}
