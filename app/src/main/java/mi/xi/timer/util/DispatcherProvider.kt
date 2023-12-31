package mi.xi.timer.util

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherProvider @Inject constructor() {
    fun main() = Dispatchers.Main
    fun io() = Dispatchers.IO
    fun default() = Dispatchers.Default
}