@file:OptIn(ExperimentalMaterial3Api::class)

package mi.xi.timer.ui.screens.login.screens

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import mi.xi.timer.R
import mi.xi.timer.ui.components.CenterColumn
import mi.xi.timer.ui.components.ErrorText
import mi.xi.timer.ui.components.OneLineTextField

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onSignupClick: () -> Unit
) {
    CenterColumn {
        Text(text = stringResource(R.string.log_in))
        OneLineTextField(
            value = viewModel.username,
            label = stringResource(R.string.username),
            onValueChange = viewModel::updateUsername
        )
        OneLineTextField(
            value = viewModel.password,
            label = stringResource(R.string.password),
            onValueChange = viewModel::updatePassword,
            keyboardType = KeyboardType.Password
        )
        if (viewModel.error.isNotEmpty()) {
            ErrorText(text = viewModel.error)
        }
        Button(onClick = viewModel::login) {
            Text(text = stringResource(R.string.login))
        }
        OutlinedButton(onClick = onSignupClick) {
            Text(text = stringResource(R.string.sign_up))
        }
    }
}