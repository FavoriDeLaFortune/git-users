package com.example.git_users.ui.start_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.git_users.ui.components.ProfileSmallCard
import com.example.git_users.ui.model.Profile
import com.example.git_users.ui.model.UiState
import com.example.git_users.ui.start_screen.stateholder.StartScreenViewModel

@Composable
fun StartScreen() {
    val viewModel: StartScreenViewModel = hiltViewModel()
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
    when (uiState) {
        UiState.Initial -> {
            Log.d("logigi", "initial state")
        }

        UiState.Error -> {}
        is UiState.ProfileList -> {
            Log.d("logigi", "list: ${(uiState as UiState.ProfileList).list}")
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(horizontal = 32.dp)
            ) {
                items((uiState as UiState.ProfileList).list) { profile ->
                    ProfileSmallCard(
                        login = profile.login,
                        avatarUrl = profile.avatarUrl,
                        reposCount = profile.reposCount,
                        followersCount = profile.followers
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenProfileListPreview() {
    val list = listOf(
        Profile("123", "https://avatars.githubusercontent.com/u/1?v=4"),
        Profile("1243", "https://avatars.githubusercontent.com/u/2?v=4"),
        Profile("1213123", "https://avatars.githubusercontent.com/u/3?v=4"),
        Profile("14325423", "https://avatars.githubusercontent.com/u/4?v=4"),
        Profile("1432423", "https://avatars.githubusercontent.com/u/5?v=4"),
        Profile("1131323", "https://avatars.githubusercontent.com/u/6?v=4"),
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(horizontal = 32.dp)
    ) {
        items(list) { profile ->
            ProfileSmallCard(
                login = profile.login,
                avatarUrl = profile.avatarUrl,
                reposCount = profile.reposCount,
                followersCount = profile.followers
            )
        }
    }
}