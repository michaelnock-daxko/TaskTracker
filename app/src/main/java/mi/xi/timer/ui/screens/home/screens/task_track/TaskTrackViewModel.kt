package mi.xi.timer.ui.screens.home.screens.task_track

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mi.xi.timer.data.entities.Task
import mi.xi.timer.data.repositories.TaskRepository
import mi.xi.timer.util.Timer
import javax.inject.Inject

@HiltViewModel
class TaskTrackViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val timer: Timer
) : ViewModel() {
    var task by mutableStateOf<Task?>(null)
    var elapsedMillis by mutableStateOf(0L)
    val isPaused = timer.isPaused

    fun startTimer() {
        timer.start()
        viewModelScope.launch {
            while (!timer.isPaused.value) {
                elapsedMillis = timer.elapsedTimeMs
                delay(10)
            }
        }
    }

    fun pauseTimer() {
        timer.pause()
        elapsedMillis = timer.elapsedTimeMs
    }

    fun togglePause() {
        if (timer.isPaused.value) startTimer() else pauseTimer()
    }

    fun fetchTask(taskId: Long) {
        viewModelScope.launch {
            task = taskRepository.fetchTask(taskId)
        }
    }

    fun saveEntry() {
        viewModelScope.launch {
            task?.let {
                timer.pause()
                taskRepository.createTaskEvent(taskId = it.id, millis = timer.elapsedTimeMs)
            }
        }
    }
}

