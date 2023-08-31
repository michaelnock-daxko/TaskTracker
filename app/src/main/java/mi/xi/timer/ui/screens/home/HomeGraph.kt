@file:OptIn(ExperimentalAnimationApi::class)

package mi.xi.timer.ui.screens.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentScope.SlideDirection.*
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import mi.xi.timer.ui.screens.HomeScreen
import mi.xi.timer.ui.screens.home.screens.home.HomeScreen
import mi.xi.timer.ui.screens.home.screens.task_create.TaskCreateScreen
import mi.xi.timer.ui.screens.home.screens.task_detail.TaskDetailScreen
import mi.xi.timer.ui.screens.home.screens.task_track.TaskTrackScreen
import mi.xi.timer.util.navigateTop

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(
        route = HomeScreen.route,
        startDestination = HomeMain.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        }
    ) {
        composable(HomeMain.route) {
            HomeScreen(
                onTaskClicked = {
                    val route = TaskDetail.route(it.id)
                    navController.navigate(route)
                },
                onAddTaskClicked = { navController.navigate(TaskCreate.route) }
            )
        }
        composable(
            TaskCreate.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
        ) {
            TaskCreateScreen(
                onTaskCreated = { navController.navigateTop(HomeScreen.route) },
                onBackPressed = { navController.popBackStack() }
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
