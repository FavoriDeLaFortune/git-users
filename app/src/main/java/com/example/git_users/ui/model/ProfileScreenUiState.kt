package com.example.git_users.ui.model

sealed interface ProfileScreenUiState {
    data object Error: ProfileScreenUiState
    data class ProfileInfo(val profile: Profile, val followersUiState: FollowersUiState): ProfileScreenUiState
}

sealed interface FollowersUiState {
    data object Error: FollowersUiState
    data class FollowersList(val followersList: List<Profile>): FollowersUiState
}