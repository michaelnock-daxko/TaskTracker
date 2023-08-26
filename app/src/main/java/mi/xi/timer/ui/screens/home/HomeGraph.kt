package mi.xi.timer.ui.screens.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import mi.xi.timer.ui.screens.HomeScreen
import mi.xi.timer.ui.screens.home.screens.HomeScreen

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(
        route = HomeScreen.route,
        startDestination = HomeMain.route
    ) {
        composable(HomeMain.route) {
            HomeScreen()
        }
    }
}
