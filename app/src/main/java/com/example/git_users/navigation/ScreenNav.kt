package com.example.git_users.navigation



sealed class ScreenNav(val route: String) {
    data object UsersScreenNav : ScreenNav(USERS_SCREEN_ROUTE)
    data object ProfileScreenNav : ScreenNav(PROFILE_SCREEN_ROUTE)

    companion object {
        private const val USERS_SCREEN_ROUTE ="users_screen/"
        const val PROFILE_ITEM_ARGUMENT = "profile"
        private const val PROFILE_SCREEN_ROUTE ="profile_screen/profile={$PROFILE_ITEM_ARGUMENT}"
    }
}