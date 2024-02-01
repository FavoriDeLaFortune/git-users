package com.example.git_users.network

import com.example.git_users.data.model.ProfileNetworkModel
import com.example.git_users.data.model.UsersListItemNetworkModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("users")
    suspend fun getUsersList(): Response<List<UsersListItemNetworkModel>>

    @GET("users/{login}")
    suspend fun getProfileInfo(@Path("login", encoded = true) login: String): Response<ProfileNetworkModel>

    @GET("users/{login}/followers")
    suspend fun getFollowersByLogin(@Path("login", encoded = true) login: String): Response<List<UsersListItemNetworkModel>>
}