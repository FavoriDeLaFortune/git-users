package com.example.git_users.ui.model


data class Profile(
    val login: String,
    val avatarUrl: String,
    val extendedInfo: ExtendedInfoState? = null,
)

sealed class ExtendedInfoState {
    data class Success(
        val name: String? = null,
        val company: String? = null,
        val email: String? = null,
        val blog: String? = null,
        val location: String? = null,
        val reposCount: Int,
        val followers: Int,
        val bio: String? = null,
    ): ExtendedInfoState()
    data class Error(val code: Int, val message: String): ExtendedInfoState()
}