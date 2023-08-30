@file:OptIn(ExperimentalAnimationApi::class)

package mi.xi.timer.ui.screens.signup

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import mi.xi.timer.ui.screens.LoginScreen
import mi.xi.timer.ui.screens.SignupScreen
import mi.xi.timer.ui.screens.signup.screens.SignupMainScreen
import mi.xi.timer.ui.screens.signup.screens.SignupPasswordScreen
import mi.xi.timer.util.navigateTop

fun NavGraphBuilder.signupGraph(navController: NavController) {
    navigation(
        route = SignupScreen.route,
        startDestination = SignupMain.route
    ) {
        composable(SignupMain.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            }
        ) {
            SignupMainScreen(
                onUsernameCreated = {
                    val route = SignupPassword.route(it)
                    navController.navigate(route)
                },
                onLoginClicked = { navController.navigateTop(LoginScreen.route) }
            )
        }
        composable(
            route = SignupPassword.route,
            arguments = SignupPassword.arguments
        ) {
            val username = it.arguments?.getString(SignupPassword.username)
            if (username == null) {
                navController.popBackStack()
            } else {
                SignupPasswordScreen(username)
            }
        }
    }
}