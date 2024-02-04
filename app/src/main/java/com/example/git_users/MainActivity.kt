package com.example.git_users

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.git_users.navigation.ScreenNav
import com.example.git_users.navigation.utils.addProfileScreenRoute
import com.example.git_users.navigation.utils.addStartScreenRoute
import com.example.git_users.services.AppConnectivityManager
import com.example.git_users.ui.components.buttons.BackTopAppBarButton
import com.example.git_users.ui.components.snackbars.DismissSnackbar
import com.example.git_users.ui.components.texts.LargeText
import com.example.git_users.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val connectivityManager by lazy { AppConnectivityManager(this, lifecycleScope) }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
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
                val usersText = stringResource(id = R.string.users)
                val scaffoldTitle = remember{ mutableStateOf(usersText) }
                val scaffoldBackButton = remember{ mutableStateOf(false) }
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { LargeText(text = scaffoldTitle.value) },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                            navigationIcon = { if (scaffoldBackButton.value) BackTopAppBarButton(navController = navController) },
                        )
                    },
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = ScreenNav.UsersScreenNav.route
                    ) {
                        addProfileScreenRoute(
                            navController = navController,
                            showDismissSnackbar = showDismissSnackbar,
                            paddingValues = paddingValues,
                            connectionIsOnline = connectionIsOnline,
                        ) { login ->
                            scaffoldTitle.value = login
                            scaffoldBackButton.value = true
                        }
                        addStartScreenRoute(
                            navController = navController,
                            showDismissSnackbar = showDismissSnackbar,
                            connectionIsOnline = connectionIsOnline,
                            paddingValues = paddingValues
                        ) {
                            scaffoldTitle.value = usersText
                            scaffoldBackButton.value = false
                        }
                    }
                }
            }
        }
    }
}