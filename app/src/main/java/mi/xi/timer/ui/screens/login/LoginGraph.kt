package mi.xi.timer.ui.screens.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import mi.xi.timer.ui.screens.LoginScreen
import mi.xi.timer.ui.screens.login.screens.LoginScreen

fun NavGraphBuilder.loginGraph(navController: NavController) {
    navigation(
        route = LoginScreen.route,
        startDestination = LoginMain.route
    ) {
        composable(LoginMain.route) {
            LoginScreen()
        }
    }
}