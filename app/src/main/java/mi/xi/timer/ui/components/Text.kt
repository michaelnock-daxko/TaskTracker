@file:OptIn(ExperimentalMaterial3Api::class)

package mi.xi.timer.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import mi.xi.timer.ui.theme.ColorError

@Composable
fun ErrorText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = ColorError
    )
}

@Composable
fun OneLineTextField(
    value: String,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    val isPasswordType = keyboardType == KeyboardType.Password || keyboardType == KeyboardType.NumberPassword
    var isVisible by remember { mutableStateOf(false) }
    val transformation = if (isVisible || !isPasswordType) VisualTransformation.None else PasswordVisualTransformation()
    OutlinedTextField(
        value = value,
        label = { Text(text = label) },
        onValueChange = onValueChange,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = transformation,
        trailingIcon = {
            if (isPasswordType) {
                val image = if (isVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { isVisible = !isVisible }) {
                    Icon(imageVector = image, contentDescription = "Password visibility toggle")
                }
            }
        }
    )
}