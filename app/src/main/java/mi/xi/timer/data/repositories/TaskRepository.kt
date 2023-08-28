package mi.xi.timer.data.repositories

import mi.xi.timer.data.UserManager
import mi.xi.timer.data.entities.Task
import mi.xi.timer.data.entities.TaskDao
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao,
    private val userManager: UserManager
) {
    suspend fun fetchAll() = taskDao.getAllFromUser(userManager.currentUser.value?.username)

    suspend fun fetchTask(taskId: Long): Task? {
        return taskDao.getTask(taskId)
    }

    suspend fun createTask(name: String): Task? {
        val username = userManager.currentUser.value?.username ?: return null
        val task = Task(name = name, username = username)
        val taskId = taskDao.insert(task)
        return task.copy(id = taskId)
    }
}