package com.example.git_users.navigation

private const val USERS_SCREEN_ROUTE ="123"
private const val PROFILE_SCREEN_ROUTE ="1234"

sealed class ScreenNav(val route: String) {
    data object UsersScreenNav : ScreenNav(USERS_SCREEN_ROUTE)
    data object ProfileScreenNav : ScreenNav(PROFILE_SCREEN_ROUTE)
}