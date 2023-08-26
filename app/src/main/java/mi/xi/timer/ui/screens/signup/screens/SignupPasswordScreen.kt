package mi.xi.timer.ui.screens.signup.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mi.xi.timer.ui.components.CenterColumn

@Composable
fun SignupPasswordScreen () {
    CenterColumn {
        Text(
            modifier = Modifier,
            text = "Let's Create a Secure Password"
        )
    }
}