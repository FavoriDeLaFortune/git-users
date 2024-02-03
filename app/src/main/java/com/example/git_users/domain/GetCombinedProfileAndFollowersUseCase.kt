package com.example.git_users.domain

import com.example.git_users.ui.profile_screen.contract.ProfileScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCombinedProfileAndFollowersUseCase @Inject constructor(
    private val getProfileInfoByLoginUseCase: GetProfileInfoByLoginUseCase,
    private val getFollowersByLoginUseCase: GetFollowersByLoginUseCase
) {

    operator fun invoke(login: String): Flow<ProfileScreenUiState> = combine(
        getProfileInfoByLoginUseCase.invoke(login = login),
        getFollowersByLoginUseCase.invoke(login = login)
    ) { profileState, followersState ->
        when (profileState) {
            is ProfileScreenUiState.ProfileInfo -> {
                profileState.copy(followersUiState = followersState)
            }
            is ProfileScreenUiState.Error -> {
                ProfileScreenUiState.Error(code = profileState.code, message = profileState.message)
            }
            // This case is unexpected. ProfileScreenUiState should be mapped in ViewModel layer.
            is ProfileScreenUiState.Initial -> {
                ProfileScreenUiState.Initial
            }
        }
    }.flowOn(Dispatchers.IO)
}