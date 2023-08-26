package mi.xi.timer.ui.screens.signup.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mi.xi.timer.data.TimerResult
import mi.xi.timer.data.UserManager
import javax.inject.Inject

@HiltViewModel
class SignupPasswordViewModel @Inject constructor(
    private val userManager: UserManager
) : ViewModel() {
    private val specialCharacters = ".,#@$!?"

    var password by mutableStateOf("")
    var errors = mutableStateListOf<String>()

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun checkPassword(username: String) {
        val errorMsgs = checkPasswordErrors()
        if (errorMsgs.isEmpty()) {
            viewModelScope.launch {
                val result = userManager.signUp(username, password)
                when(result) {
                    is TimerResult.Failure -> {
                        errors.clear()
                        errors.add(result.error)
                    }
                    else -> Unit
                }
            }
        } else {
            errors.clear()
            errors.addAll(errorMsgs)
        }
    }

    // region Password verification ------------------------------------------------------------------
    private fun String.isLongEnough() = (length >= 8) to "Minimum 8 characters"
    private fun String.hasLower() = any(Char::isLowerCase) to "Must have lowercase characters"
    private fun String.hasUpper() = any(Char::isUpperCase) to "Must have uppercase characters"
    private fun String.hasDigit() = any(Char::isDigit) to "Must have digit"
    private fun String.hasSpecialChar() =
        any { it in specialCharacters } to "Must contain at least one of: $specialCharacters"

    private fun checkPasswordErrors(): List<String> {
        password.apply {
            val validations = listOf(
                isLongEnough(), hasLower(), hasUpper(), hasDigit(), hasSpecialChar()
            )
            // First == if check passed, second == error message
            return validations.filter { !it.first }.map { it.second }
        }
    }
    // endregion Password verification ---------------------------------------------------------------
}