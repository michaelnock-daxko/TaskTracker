package mi.xi.timer.ui.screens.signup

import androidx.navigation.NavType
import androidx.navigation.navArgument
import mi.xi.timer.ui.screens.Screen

object SignupMain : Screen {
    override val route = "signup_main"
}

object SignupPassword : Screen {
    private const val routeName = "signup_password"

    // arguments
    const val username = "username"
    val arguments = listOf(navArgument(username) { type = NavType.StringType })

    override val route = "$routeName/{$username}"
    fun route(username: String) = "$routeName/$username"
}
