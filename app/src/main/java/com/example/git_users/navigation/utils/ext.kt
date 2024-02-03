package com.example.git_users.navigation.utils

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.git_users.navigation.ScreenNav
import com.example.git_users.ui.profile_screen.ProfileScreen
import com.example.git_users.ui.start_screen.StartScreen

fun NavGraphBuilder.navigateToProfileScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    showDismissSnackbar: @Composable (String) -> Unit,
    connectionIsOnline: Boolean
) {
    composable(
        route = ScreenNav.ProfileScreenNav.route,
        arguments = listOf(navArgument(ScreenNav.PROFILE_ITEM_ARGUMENT) {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        val login = backStackEntry.arguments?.getString(ScreenNav.PROFILE_ITEM_ARGUMENT)
        if (login != null) {
            ProfileScreen(
                navController = navController,
                snackbarHostState = snackbarHostState,
                login = login,
                showDismissSnackbar = showDismissSnackbar,
                connectionIsOnline = connectionIsOnline
            )
        }
    }
}

fun NavGraphBuilder.navigateToStartScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    showDismissSnackbar: @Composable (String) -> Unit,
    connectionIsOnline: Boolean
) {
    composable(ScreenNav.UsersScreenNav.route) {
        StartScreen(
            navController = navController,
            snackbarHostState = snackbarHostState,
            showDismissSnackbar = showDismissSnackbar,
            connectionIsOnline = connectionIsOnline
        )
    }
}