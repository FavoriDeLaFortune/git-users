package com.example.git_users.ui.model


data class Profile(
    val login: String,
    val avatarUrl: String,
    val name: String? = null,
    val company: String? = null,
    val email: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val reposCount: Int? = null,
    val followers: Int? = null,
    val bio: String? = null,
)
