package mi.xi.timer.ui.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import mi.xi.timer.ui.screens.HomeScreen
import mi.xi.timer.ui.screens.home.screens.home.HomeScreen
import mi.xi.timer.ui.screens.home.screens.task_detail.TaskDetailScreen
import mi.xi.timer.ui.screens.home.screens.task_track.TaskTrackScreen

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(
        route = HomeScreen.route,
        startDestination = HomeMain.route
    ) {
        composable(HomeMain.route) {
            HomeScreen(
                onTaskClicked = {
                    val route = TaskDetail.route(it.id)
                    navController.navigate(route)
                }
            )
        }
        composable(route = TaskDetail.route, arguments = TaskDetail.arguments) {
            val taskId = it.arguments?.getLong(TaskDetail.task_id)
            if (taskId == null) {
                navController.popBackStack()
            } else {
                TaskDetailScreen(
                    taskId = taskId,
                    onBackPressed = { navController.popBackStack() },
                    onTrackEvent = {
                        val route = TaskTrack.route(taskId)
                        navController.navigate(route)
                    }
                )
            }
        }
        composable(route = TaskTrack.route, arguments = TaskTrack.arguments) {
            val taskId = it.arguments?.getLong(TaskDetail.task_id)
            if (taskId == null) {
                navController.popBackStack()
            } else {
                TaskTrackScreen(
                    taskId = taskId,
                    onBackPressed = { navController.popBackStack() },
                )
            }
        }
    }
}
