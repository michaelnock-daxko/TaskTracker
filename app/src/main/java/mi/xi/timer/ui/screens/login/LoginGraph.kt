@file:OptIn(ExperimentalAnimationApi::class)

package mi.xi.timer.ui.screens.login

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import mi.xi.timer.ui.screens.LoginScreen
import mi.xi.timer.ui.screens.SignupScreen
import mi.xi.timer.ui.screens.login.screens.LoginScreen
import mi.xi.timer.util.navigateTop

fun NavGraphBuilder.loginGraph(navController: NavController) {
    navigation(
        route = LoginScreen.route,
        startDestination = LoginMain.route
    ) {
        composable(LoginMain.route) {
            LoginScreen(onSignupClick = { navController.navigateTop(SignupScreen.route) })
        }
    }
}
