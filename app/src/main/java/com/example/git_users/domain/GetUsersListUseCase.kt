package com.example.git_users.domain

import android.util.Log
import com.example.git_users.data.GithubRepository
import com.example.git_users.data.IRepository
import com.example.git_users.data.model.NetworkState
import com.example.git_users.data.model.ProfileNetworkModel
import com.example.git_users.data.model.UsersListItemNetworkModel
import com.example.git_users.ui.model.Profile
import com.example.git_users.ui.model.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUsersListUseCase @Inject constructor(private val repository: IRepository) {

    operator fun invoke(): Flow<UiState> = flow {
        Log.d("logigi", "usecase invoked")
        when (val networkState = repository.getUsersList()) {
            is NetworkState.Success<List<UsersListItemNetworkModel>> -> {
                emit(UiState.ProfileList(list = networkState.body.map { Profile(login = it.login, avatarUrl = it.avatarUrl) }))
                emit(UiState.ProfileList(list = networkState.body.toListOfProfiles()))
                Log.d("logigi", "usecase emited")
            }
            is NetworkState.Error -> {
                emit(UiState.Error)
            }
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun List<UsersListItemNetworkModel>.toListOfProfiles(): List<Profile> {
        return this.map { usersListItem ->
            when (val networkState = repository.getProfileModel(usersListItem.login)) {
                is NetworkState.Success<ProfileNetworkModel> -> {
                    Profile(
                        login = usersListItem.login,
                        avatarUrl = usersListItem.avatarUrl,
                        name = networkState.body.name,
                        company = networkState.body.company,
                        email = networkState.body.email,
                        blog = networkState.body.blog,
                        location = networkState.body.location,
                        reposCount = networkState.body.reposCount,
                        followers = networkState.body.followers,
                        bio = networkState.body.bio
                    )
                }
                is NetworkState.Error -> {
                    Profile(
                        login = usersListItem.login,
                        avatarUrl = usersListItem.avatarUrl,
                    )
                }
            }
        }
    }
}