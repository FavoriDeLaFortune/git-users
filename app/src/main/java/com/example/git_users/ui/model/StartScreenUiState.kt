package com.example.git_users.ui.model

sealed interface StartScreenUiState {

    data object Initial: StartScreenUiState
    data object Error: StartScreenUiState
    data class ProfileList(val list: List<Profile>): StartScreenUiState
}
