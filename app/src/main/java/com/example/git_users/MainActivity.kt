package com.example.git_users

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.git_users.navigation.ScreenNav
import com.example.git_users.navigation.utils.navigateToProfileScreen
import com.example.git_users.navigation.utils.navigateToStartScreen
import com.example.git_users.services.AppConnectivityManager
import com.example.git_users.ui.components.snackbars.DismissSnackbar
import com.example.git_users.ui.theme.GitusersTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val connectivityManager by lazy { AppConnectivityManager(this, lifecycleScope) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitusersTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                val coroutineScope = rememberCoroutineScope()
                val connectionIsOnline by connectivityManager.connectionStateFlow.collectAsStateWithLifecycle()
                val showDismissSnackbar: @Composable (String) -> Unit = { message ->
                    DismissSnackbar(
                        snackbarHostState = snackbarHostState,
                        coroutineScope = coroutineScope,
                        message = message
                    )
                }
                NavHost(
                    navController = navController,
                    startDestination = ScreenNav.UsersScreenNav.route
                ) {
                    navigateToProfileScreen(
                        navController = navController,
                        snackbarHostState = snackbarHostState,
                        showDismissSnackbar = showDismissSnackbar,
                        connectionIsOnline = connectionIsOnline

                    )
                    navigateToStartScreen(
                        navController = navController,
                        snackbarHostState = snackbarHostState,
                        showDismissSnackbar = showDismissSnackbar,
                        connectionIsOnline = connectionIsOnline
                    )
                }
            }
        }
    }
}