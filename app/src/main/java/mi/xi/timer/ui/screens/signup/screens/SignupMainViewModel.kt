package mi.xi.timer.ui.screens.signup.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupMainViewModel @Inject constructor() : ViewModel() {
    var username by mutableStateOf("")
    var error by mutableStateOf("")

    private val _isUsernameReady = MutableSharedFlow<Boolean>()
    val isUsernameReady = _isUsernameReady.asSharedFlow()

    fun updateUsername(newName: String) {
        username = newName
    }

    /**
     * - Length >= 4 characters
     * - Only contains letters, numbers, ._
     */
    fun checkUsername() {
        if (username.length < 4) error = "Username too short"
        else if (username.all { it.isLetterOrDigit() || it == '.' || it == '_' }) {
            viewModelScope.launch { _isUsernameReady.emit(true) }
        } else error = "Username has invalid characters"
    }
}