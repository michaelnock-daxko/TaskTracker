package mi.xi.timer.ui.screens.home.screens.task_create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mi.xi.timer.data.repositories.TaskRepository
import javax.inject.Inject

@HiltViewModel
class TaskCreateViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {
    var errors by mutableStateOf("")
    var taskName by mutableStateOf("")
    var isTaskAdded by mutableStateOf(false)

    fun updateTaskName(newName: String) {
        taskName = newName
    }

    fun createTask() {
        viewModelScope.launch {
            if (taskName.isBlank()) errors = "Name must not be empty"
            taskRepository.createTask(taskName.trim())
            isTaskAdded = true
        }
    }

    fun clearErrors() {
        errors = ""
    }
}
