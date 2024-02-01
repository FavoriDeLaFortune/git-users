package com.example.git_users.ui.profile_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.git_users.R
import com.example.git_users.ui.components.buttons.BackTopAppBarButton
import com.example.git_users.ui.components.cards.ProfileLargeCard
import com.example.git_users.ui.components.cards.ProfileSmallCard
import com.example.git_users.ui.components.texts.LargeText
import com.example.git_users.ui.model.FollowersUiState
import com.example.git_users.ui.model.ProfileScreenUiState
import com.example.git_users.ui.profile_screen.stateholder.ProfileScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController, login: String) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    LargeText(text = login)
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                navigationIcon = { BackTopAppBarButton(navController = navController) }
            )
        }
    ) { topBarPaddingValues ->
        val viewModel: ProfileScreenViewModel = hiltViewModel()
        viewModel.setLogin(login = login)
        val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
        when (uiState) {
            is ProfileScreenUiState.ProfileInfo -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
                ) {
                    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                        ProfileLargeCard(
                            profile = (uiState as ProfileScreenUiState.ProfileInfo).profile,
                            topBarPaddingValues
                        )
                    }
                    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                        LargeText(
                            text = stringResource(id = R.string.subscribers),
                            modifier = Modifier.padding(start = 32.dp, end = 32.dp, bottom = 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                    when (val followersState =
                        (uiState as ProfileScreenUiState.ProfileInfo).followersUiState) {
                        FollowersUiState.Error -> {

                        }

                        is FollowersUiState.FollowersList -> {
                            items(followersState.followersList) { profile ->
                                ProfileSmallCard(
                                    login = profile.login,
                                    avatarUrl = profile.avatarUrl,
                                    reposCount = profile.reposCount,
                                    followersCount = profile.followers,
                                    onClick = {
                                        navController.navigate("profile_screen/profile=${profile.login}")
                                    }
                                )
                            }
                        }
                    }

                }
            }

            is ProfileScreenUiState.Error -> {}
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {

}