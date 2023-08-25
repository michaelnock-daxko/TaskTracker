package mi.xi.timer.ui.screens.login.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mi.xi.timer.data.TimerResult
import mi.xi.timer.data.UserManager
import mi.xi.timer.util.DispatcherProvider
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userManager: UserManager,
    private val dispatchers: DispatcherProvider
) : ViewModel() {
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var error by mutableStateOf("")

    fun updateUsername(newName: String) {
        username = newName
    }

    fun updatePassword(newPassword: String) {
        password = newPassword
    }

    fun login() {
        viewModelScope.launch(dispatchers.io()) {
            val result = userManager.login(username, password)
            when (result) {
                is TimerResult.Failure -> error = result.error
                else -> Unit
            }
        }
    }
}