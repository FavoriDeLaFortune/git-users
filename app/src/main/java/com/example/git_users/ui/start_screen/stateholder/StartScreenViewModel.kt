package com.example.git_users.ui.start_screen.stateholder

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.git_users.domain.GetUsersListUseCase
import com.example.git_users.ui.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StartScreenViewModel @Inject constructor(
    getUsersListUseCase: GetUsersListUseCase
) : ViewModel() {
    private var _uiStateFlow = MutableStateFlow<UiState>(UiState.Initial)
    val uiStateFlow = _uiStateFlow

    init {
        getUsersListUseCase.invoke().onEach { _uiStateFlow.value = it
            Log.d("logigi", "$it")
        }.launchIn(viewModelScope)
    }
}