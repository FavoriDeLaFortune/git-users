package com.example.git_users

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.git_users.navigation.ScreenNav
import com.example.git_users.ui.profile_screen.ProfileScreen
import com.example.git_users.ui.start_screen.StartScreen
import com.example.git_users.ui.theme.GitusersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitusersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenNav.UsersScreenNav.route
                    ) {
                        composable(
                            route = ScreenNav.ProfileScreenNav.route,
                            arguments = listOf(navArgument(ScreenNav.PROFILE_ITEM_ARGUMENT) {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val login = backStackEntry.arguments?.getString(ScreenNav.PROFILE_ITEM_ARGUMENT)
                            if (login != null) {
                                ProfileScreen(navController = navController, login = login)
                            }
                        }
                        composable(ScreenNav.UsersScreenNav.route) {
                            StartScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}