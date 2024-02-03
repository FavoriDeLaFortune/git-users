package com.example.git_users.ui.start_screen.contract

import com.example.git_users.ui.model.Profile

sealed interface StartScreenUiState {

    data object Initial: StartScreenUiState
    data class Error(val code: Int, val message: String): StartScreenUiState
    data class ProfileList(val list: List<Profile>): StartScreenUiState
}
