package mi.xi.timer.ui.screens.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
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
        composable(SignupMain.route) {
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