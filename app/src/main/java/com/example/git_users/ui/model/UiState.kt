package com.example.git_users.ui.model

sealed interface UiState {

    data object Initial: UiState
    data object Error: UiState
    data class ProfileList(val list: List<Profile>): UiState
}
