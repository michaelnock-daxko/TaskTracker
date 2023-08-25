package mi.xi.timer.data

sealed class TimerResult<T> {
    data class Success<T>(val data: T) : TimerResult<T>()
    data class Failure<T>(val error: String) : TimerResult<T>()
}