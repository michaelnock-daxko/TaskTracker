package mi.xi.timer.ui.screens.home.screens.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mi.xi.timer.data.UserManager
import mi.xi.timer.data.entities.Task
import mi.xi.timer.data.repositories.TaskRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userManager: UserManager,
    private val taskRepository: TaskRepository
) : ViewModel() {
    var tasks = mutableStateListOf<Task>()

    init {
        viewModelScope.launch {
            tasks.addAll(taskRepository.fetchAll())
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskRepository.updateTask(task)
            val index = tasks.indexOfFirst { it.id == task.id }
            if (index >= 0) {
                tasks.removeAt(index)
                tasks.add(index, task)
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
            tasks.remove(task)
        }
    }

    fun logOut() {
        viewModelScope.launch { userManager.logOut() }
    }
}
