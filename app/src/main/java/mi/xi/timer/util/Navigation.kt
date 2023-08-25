package mi.xi.timer.util

import androidx.navigation.NavController

/**
 * Pops all navigation stack entries off the stack and navigates to the route, leaving that route
 * as the only item in the navigation stack.
 */
fun NavController.navigateTop(route: String) {
    navigate(route) {
        popUpTo(graph.id) { inclusive = true }
    }
}
