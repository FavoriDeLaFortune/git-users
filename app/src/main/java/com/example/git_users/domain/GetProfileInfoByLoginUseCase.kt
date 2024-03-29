package com.example.git_users.domain

import com.example.git_users.data.IRepository
import com.example.git_users.data.model.NetworkState
import com.example.git_users.data.model.ProfileNetworkModel
import com.example.git_users.domain.utils.toProfile
import com.example.git_users.ui.profile_screen.contract.FollowersUiState
import com.example.git_users.ui.profile_screen.contract.ProfileScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetProfileInfoByLoginUseCase @Inject constructor(private val repository: IRepository) {

    operator fun invoke(login: String): Flow<ProfileScreenUiState> = flow {
        when (val networkState = repository.getProfileModel(login = login)) {
            is NetworkState.Success<ProfileNetworkModel> -> {
                emit(ProfileScreenUiState.ProfileInfo(networkState.body.toProfile(), FollowersUiState.Initial))
            }
            is NetworkState.Error -> {
                emit(ProfileScreenUiState.Error(code = networkState.code, message = networkState.message))
            }
        }
    }.flowOn(Dispatchers.IO)
}