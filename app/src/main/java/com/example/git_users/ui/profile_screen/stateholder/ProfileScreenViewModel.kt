package com.example.git_users.ui.profile_screen.stateholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.git_users.domain.GetCombinedProfileAndFollowersUseCase
import com.example.git_users.ui.model.ProfileScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val getCombinedProfileAndFollowersUseCase: GetCombinedProfileAndFollowersUseCase
) : ViewModel() {
    private var loginFlow = MutableStateFlow("")
    private var _uiStateFlow = MutableStateFlow<ProfileScreenUiState>(ProfileScreenUiState.Error)
    val uiStateFlow = _uiStateFlow

    init {
        loginFlow.onEach { login ->
            getCombinedProfileAndFollowersUseCase.invoke(login).collect { _uiStateFlow.value = it }
        }.launchIn(viewModelScope)
    }

    fun setLogin(login: String) {
        viewModelScope.launch {
            loginFlow.value = login
        }
    }
}