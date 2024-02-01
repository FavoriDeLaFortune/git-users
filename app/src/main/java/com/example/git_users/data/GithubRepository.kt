package com.example.git_users.data

import com.example.git_users.data.model.NetworkState
import com.example.git_users.data.model.ProfileNetworkModel
import com.example.git_users.data.model.UsersListItemNetworkModel
import com.example.git_users.network.GithubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GithubRepository @Inject constructor(private val githubApi: GithubApi) : IRepository() {

    override suspend fun getUsersList(): NetworkState<List<UsersListItemNetworkModel>> = withContext(Dispatchers.IO) {
        val response = githubApi.getUsersList()
        return@withContext resolveBodyFromResponse(response = response)
    }

    override suspend fun getProfileModel(login: String): NetworkState<ProfileNetworkModel> = withContext(Dispatchers.IO) {
        val response = githubApi.getProfileInfo(login = login)
        return@withContext resolveBodyFromResponse(response = response)
    }

    override suspend fun getFollowersByLogin(login: String): NetworkState<List<UsersListItemNetworkModel>> = withContext(Dispatchers.IO) {
        val response = githubApi.getFollowersByLogin(login = login)
        return@withContext resolveBodyFromResponse(response = response)
    }

    override fun <T> resolveBodyFromResponse(response: Response<T>): NetworkState<T> {
        return if (response.isSuccessful && response.body() != null) {
            // used strict operator cause response body isn't null in this section
            NetworkState.Success(response.body()!!)
        } else {
            NetworkState.Error
        }
    }

}