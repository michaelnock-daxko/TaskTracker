package mi.xi.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mi.xi.timer.data.UserManager
import mi.xi.timer.ui.screens.HomeScreen
import mi.xi.timer.ui.screens.LoginScreen
import mi.xi.timer.ui.screens.home.homeGraph
import mi.xi.timer.ui.screens.login.loginGraph
import mi.xi.timer.ui.screens.signup.signupGraph
import mi.xi.timer.ui.theme.TimerTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val currentUser by userManager.currentUser.collectAsState()

            LaunchedEffect(Unit) { userManager.checkUserLoggedIn() }

            val start = if (currentUser == null) LoginScreen.route else HomeScreen.route
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
    NavHost(navController = navController, startDestination = start) {
        loginGraph(navController = navController)
        signupGraph(navController = navController)
        homeGraph(navController = navController)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TimerTheme {
        Greeting("Android")
    }
}