package mi.xi.timer.ui.screens.home

import androidx.navigation.NavType
import androidx.navigation.navArgument
import mi.xi.timer.ui.screens.Screen

object HomeMain : Screen {
    override val route = "home_main"
}

object TaskDetail : Screen {
    private const val routeName = "task_detail"

    // arguments
    const val task_id = "task_id"
    val arguments = listOf(navArgument(task_id) { type = NavType.LongType })

    override val route = "$routeName/{$task_id}"
    fun route(taskId: Long) = "$routeName/$taskId"
}

object TaskTrack : Screen {
    private const val routeName = "task_track"

    // arguments
    const val task_id = "task_id"
    val arguments = listOf(navArgument(task_id) { type = NavType.LongType })

    override val route = "$routeName/{$task_id}"
    fun route(taskId: Long) = "$routeName/$taskId"
}