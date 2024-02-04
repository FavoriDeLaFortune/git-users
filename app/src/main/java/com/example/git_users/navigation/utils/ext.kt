package com.example.git_users.navigation.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.git_users.navigation.ScreenNav
import com.example.git_users.ui.profile_screen.ProfileScreen
import com.example.git_users.ui.start_screen.StartScreen

fun NavGraphBuilder.addProfileScreenRoute(
    navController: NavHostController,
    showDismissSnackbar: @Composable (String) -> Unit,
    connectionIsOnline: Boolean,
    paddingValues: PaddingValues,
    scaffoldConfigure: @Composable (String) -> Unit
) {
    composable(
        route = ScreenNav.ProfileScreenNav.route,
        arguments = listOf(navArgument(ScreenNav.PROFILE_ITEM_ARGUMENT) {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        val login = backStackEntry.arguments?.getString(ScreenNav.PROFILE_ITEM_ARGUMENT)
        if (login != null) {
            scaffoldConfigure.invoke(login)
            ProfileScreen(
                navController = navController,
                login = login,
                showDismissSnackbar = showDismissSnackbar,
                connectionIsOnline = connectionIsOnline,
                paddingValues = paddingValues
            )
        }
    }
}

fun NavGraphBuilder.addStartScreenRoute(
    navController: NavHostController,
    showDismissSnackbar: @Composable (String) -> Unit,
    connectionIsOnline: Boolean,
    paddingValues: PaddingValues,
    scaffoldConfigure: @Composable () -> Unit
) {
    composable(ScreenNav.UsersScreenNav.route) {
        scaffoldConfigure.invoke()
        StartScreen(
            navController = navController,
            showDismissSnackbar = showDismissSnackbar,
            connectionIsOnline = connectionIsOnline,
            paddingValues = paddingValues
        )
    }
}

fun NavHostController.navigateToProfileScreen(login: String) {
    this.navigate("profile_screen/profile=$login")
}

