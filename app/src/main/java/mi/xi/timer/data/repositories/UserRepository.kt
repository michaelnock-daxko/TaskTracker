package mi.xi.timer.data.repositories

import mi.xi.timer.data.local.DataManager
import mi.xi.timer.data.model.TimerUser
import javax.inject.Inject

class UserRepository @Inject constructor(private val dataManager: DataManager) {
    suspend fun userList(): List<TimerUser>? {
        val users = dataManager.getObject<List<TimerUser>>("users")
        return users
    }

    suspend fun login(username: String, password: String) {

    }
}