@file:OptIn(ExperimentalAnimationApi::class)

package mi.xi.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import mi.xi.timer.data.UserManager
import mi.xi.timer.ui.screens.HomeScreen
import mi.xi.timer.ui.screens.LoginScreen
import mi.xi.timer.ui.screens.home.homeGraph
import mi.xi.timer.ui.screens.login.loginGraph
import mi.xi.timer.ui.screens.signup.signupGraph
import mi.xi.timer.ui.screens.splash.SplashScreen
import mi.xi.timer.ui.theme.TimerTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            val currentUser by userManager.currentUser.collectAsState()
            var userCheckComplete by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                userManager.checkUserLoggedIn()
                userCheckComplete = true
            }

            val start = if (!userCheckComplete) "splash"
            else if (currentUser != null) HomeScreen.route
            else LoginScreen.route

            TimerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavHost(start = start, navController = navController)
                }
            }
        }
    }
}

@Composable
fun MainNavHost(
    start: String,
    navController: NavHostController
) {
    AnimatedNavHost(navController = navController, startDestination = start) {
        composable("splash") { SplashScreen() }
        loginGraph(navController = navController)
        signupGraph(navController = navController)
        homeGraph(navController = navController)
    }
}
