package mi.xi.timer.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import mi.xi.timer.data.local.DataManager
import mi.xi.timer.data.model.TimerUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor(
    private val dataManager: DataManager
) {
    private val _currentUser = MutableStateFlow<TimerUser?>(null)
    val currentUser = _currentUser.asStateFlow()

    suspend fun checkUserLoggedIn() {
        val username = dataManager.getString("user") ?: return
        val user = getUsers().firstOrNull { it.username == username } ?: return
        _currentUser.value = user
    }

    suspend fun login(username: String, password: String): TimerResult<TimerUser> {
        val users = getUsers()
        val user = users.firstOrNull { it.username == username }
            ?: return TimerResult.Failure("User not found")
        return if (user.passwordHash == password.hashCode().toString()) {
            dataManager.setString("user", user.username)
            _currentUser.value = user
            TimerResult.Success(user)
        } else {
            TimerResult.Failure("Invalid password")
        }
    }

    suspend fun signUp(username: String, password: String): TimerResult<Unit> {
        val users = getUsers().toMutableList()
        if (users.any { it.username == username }) return TimerResult.Failure("Username taken")
        val newUser = TimerUser(username, password.hashCode().toString())
        users.add(newUser)
        dataManager.setObject("users", users)
        dataManager.setString("user", username)
        _currentUser.value = newUser
        return TimerResult.Success(Unit)
    }

    suspend fun logOut() {
        dataManager.setString("user", null)
        _currentUser.value = null
    }

    // region Helper functions ---------------------------------------------------------------------
    private suspend fun getUsers(): List<TimerUser> {
        return dataManager.getObject("users") ?: emptyList()
    }
    // endregion Helper functions ------------------------------------------------------------------
}