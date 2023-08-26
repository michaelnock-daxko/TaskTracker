package mi.xi.timer.ui.screens.home.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mi.xi.timer.data.UserManager
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userManager: UserManager
) : ViewModel() {
    fun logOut() {
        viewModelScope.launch { userManager.logOut() }
    }
}
