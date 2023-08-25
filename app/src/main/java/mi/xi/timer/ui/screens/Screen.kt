package mi.xi.timer.ui.screens

interface Screen {
    val route: String
}

object HomeScreen : Screen {
    override val route = "home"
}

object SignupScreen : Screen {
    override val route = "signup"
}

object LoginScreen : Screen {
    override val route = "login"
}
