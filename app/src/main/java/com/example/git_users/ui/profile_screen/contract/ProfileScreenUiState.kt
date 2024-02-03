package com.example.git_users.ui.profile_screen.contract

import com.example.git_users.ui.model.Profile

sealed interface ProfileScreenUiState {

    data object Initial: ProfileScreenUiState
    data class Error(val code: Int, val message: String): ProfileScreenUiState
    data class ProfileInfo(val profile: Profile, val followersUiState: FollowersUiState):
        ProfileScreenUiState
}

sealed interface FollowersUiState {

    data object Initial: FollowersUiState
    data class Error(val code: Int, val message: String): FollowersUiState
    data class FollowersList(val followersList: List<Profile>): FollowersUiState
}