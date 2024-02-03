package com.example.git_users.data

import com.example.git_users.data.model.NetworkState
import com.example.git_users.data.model.ProfileNetworkModel
import com.example.git_users.data.model.UsersListItemNetworkModel
import com.example.git_users.network.GithubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject

class GithubRepository @Inject constructor(private val githubApi: GithubApi) : IRepository() {

    override suspend fun getUsersList(): NetworkState<List<UsersListItemNetworkModel>> = withContext(Dispatchers.IO) {
        val response = try {
            githubApi.getUsersList()
        } catch (e: UnknownHostException) {
            return@withContext NetworkState.Error(0, "Unable to connect host")
        }
        return@withContext resolveBodyFromResponse(response = response)
    }

    override suspend fun getProfileModel(login: String): NetworkState<ProfileNetworkModel> = withContext(Dispatchers.IO) {
        val response = try {
            githubApi.getProfileInfo(login = login)
        } catch (e: UnknownHostException) {
            return@withContext NetworkState.Error(0, "Unable to connect host")
        }
        return@withContext resolveBodyFromResponse(response = response)
    }

    override suspend fun getFollowersByLogin(login: String): NetworkState<List<UsersListItemNetworkModel>> = withContext(Dispatchers.IO) {
        val response = try {
            githubApi.getFollowersByLogin(login = login)
        } catch (e: UnknownHostException) {
            return@withContext NetworkState.Error(0, "Unable to connect host")
        }
        return@withContext resolveBodyFromResponse(response = response)
    }

    override fun <T> resolveBodyFromResponse(response: Response<T>): NetworkState<T> {
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            NetworkState.Success(body = body)
        } else {
            NetworkState.Error(code = response.code(), message = response.message())
        }
    }

}