package com.example.git_users.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileNetworkModel(
    @SerialName("login")
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("name")
    val name: String? = null,
    @SerialName("company")
    val company: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("blog")
    val blog: String? = null,
    @SerialName("location")
    val location: String? = null,
    @SerialName("public_repos")
    val reposCount: Int? = null,
    @SerialName("followers")
    val followers: Int? = null,
    @SerialName("bio")
    val bio: String? = null,
)