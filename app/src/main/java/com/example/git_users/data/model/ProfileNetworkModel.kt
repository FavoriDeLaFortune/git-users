package com.example.git_users.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileNetworkModel(
    @SerialName("name")
    val name: String?,
    @SerialName("company")
    val company: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("blog")
    val blog: String?,
    @SerialName("location")
    val location: String?,
    @SerialName("public_repos")
    val reposCount: Int,
    @SerialName("followers")
    val followers: Int,
    @SerialName("bio")
    val bio: String?,
)