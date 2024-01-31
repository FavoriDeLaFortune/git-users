package com.example.git_users.data

import com.example.git_users.data.model.NetworkState
import com.example.git_users.data.model.ProfileNetworkModel
import com.example.git_users.data.model.UsersListItemNetworkModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

abstract class IRepository() {

    abstract suspend fun getUsersList(): NetworkState<List<UsersListItemNetworkModel>>

    abstract suspend fun getProfileModel(login: String): NetworkState<ProfileNetworkModel>

    protected abstract fun <T> resolveBodyFromResponse(response: Response<T>): NetworkState<T>
}