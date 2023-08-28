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

    fun addTask() {
        viewModelScope.launch {
            val task = taskRepository.createTask("task ${tasks.size}")
            if (task != null) tasks.add(task)
        }
    }

    fun logOut() {
        viewModelScope.launch { userManager.logOut() }
    }
}
