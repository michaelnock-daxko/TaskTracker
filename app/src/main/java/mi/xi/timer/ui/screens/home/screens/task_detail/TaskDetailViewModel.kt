package mi.xi.timer.ui.screens.home.screens.task_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mi.xi.timer.data.entities.Task
import mi.xi.timer.data.entities.TaskEvent
import mi.xi.timer.data.repositories.TaskRepository
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {
    var task by mutableStateOf<Task?>(null)
    var taskEvents = mutableStateListOf<TaskEvent>()

    fun deleteTaskEvent(event: TaskEvent) {
        viewModelScope.launch {
            taskRepository.deleteEvent(event)
            taskEvents.remove(event)
        }
    }

    fun fetchTask(taskId: Long) {
        viewModelScope.launch {
            task = taskRepository.fetchTask(taskId)
            taskEvents.clear()
            taskEvents.addAll(taskRepository.fetchEvents(taskId))
        }
    }
}
