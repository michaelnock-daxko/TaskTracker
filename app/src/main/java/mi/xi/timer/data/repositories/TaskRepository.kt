package mi.xi.timer.data.repositories

import mi.xi.timer.data.UserManager
import mi.xi.timer.data.entities.Task
import mi.xi.timer.data.entities.TaskDao
import mi.xi.timer.data.entities.TaskEvent
import mi.xi.timer.data.entities.TaskEventDao
import mi.xi.timer.util.TimeManager
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao,
    private val taskEventDao: TaskEventDao,
    private val userManager: UserManager,
    private val timeManager: TimeManager
) {
    suspend fun fetchAll() = taskDao.getAllFromUser(userManager.currentUser.value?.username)

    suspend fun fetchTask(taskId: Long): Task? {
        return taskDao.getTask(taskId)
    }

    suspend fun fetchEvents(taskId: Long): List<TaskEvent> = taskEventDao.getAllForTask(taskId)
    suspend fun deleteEvent(event: TaskEvent) = taskEventDao.delete(event)

    suspend fun createTaskEvent(taskId: Long, millis: Long) {
        val taskEvent =
            TaskEvent(taskId = taskId, millis = millis, creationDate = timeManager.currentTimeMs())
        taskEventDao.insert(taskEvent)
    }

    suspend fun createTask(name: String): Task? {
        val username = userManager.currentUser.value?.username ?: return null
        val task = Task(name = name, username = username)
        val taskId = taskDao.insert(task)
        return task.copy(id = taskId)
    }
}