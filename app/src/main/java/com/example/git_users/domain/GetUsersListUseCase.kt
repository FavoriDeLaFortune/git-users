package com.example.git_users.domain

import com.example.git_users.data.IRepository
import com.example.git_users.data.model.NetworkState
import com.example.git_users.data.model.ProfileNetworkModel
import com.example.git_users.data.model.UsersListItemNetworkModel
import com.example.git_users.domain.utils.toProfile
import com.example.git_users.ui.model.ExtendedInfoState
import com.example.git_users.ui.model.Profile
import com.example.git_users.ui.start_screen.contract.StartScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUsersListUseCase @Inject constructor(private val repository: IRepository) {

    operator fun invoke(): Flow<StartScreenUiState> = flow {
        when (val networkState = repository.getUsersList()) {
            is NetworkState.Success<List<UsersListItemNetworkModel>> -> {
                emit(StartScreenUiState.ProfileList(list = networkState.body.map {
                    Profile(login = it.login, avatarUrl = it.avatarUrl)
                }))
                emit(StartScreenUiState.ProfileList(list = networkState.body.toListOfProfiles()))
            }
            is NetworkState.Error -> {
                emit(StartScreenUiState.Error(code = networkState.code, message = networkState.message))
            }
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun List<UsersListItemNetworkModel>.toListOfProfiles(): List<Profile> {
        return this.map { usersListItem ->
            when (val networkState = repository.getProfileModel(usersListItem.login)) {
                is NetworkState.Success<ProfileNetworkModel> -> {
                    networkState.body.toProfile()
                }
                is NetworkState.Error -> {
                    Profile(
                        login = usersListItem.login,
                        avatarUrl = usersListItem.avatarUrl,
                        extendedInfo = ExtendedInfoState.Error(
                            code = networkState.code,
                            message = networkState.message
                        )
                    )
                }
            }
        }
    }
}