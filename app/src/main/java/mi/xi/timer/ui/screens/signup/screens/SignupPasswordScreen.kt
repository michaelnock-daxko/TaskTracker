package mi.xi.timer.ui.screens.signup.screens

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import mi.xi.timer.ui.components.CenterColumn
import mi.xi.timer.ui.components.ErrorText
import mi.xi.timer.ui.components.OneLineTextField

@Composable
fun SignupPasswordScreen(
    username: String,
    viewModel: SignupPasswordViewModel = hiltViewModel()
) {
    CenterColumn {
        Text(
            modifier = Modifier,
            text = "Let's Create a Secure Password, $username",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        OneLineTextField(
            value = viewModel.password,
            label = "Password",
            onValueChange = viewModel::updatePassword,
            keyboardType = KeyboardType.Password
        )
        if (viewModel.errors.isNotEmpty()) {
            viewModel.errors.forEach { ErrorText(text = it) }
        }
        Button(onClick = {
            viewModel.checkPassword(username)
        }) {
            Text(text = "Create Account")
        }
    }
}