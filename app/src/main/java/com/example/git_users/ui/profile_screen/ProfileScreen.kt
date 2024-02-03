package com.example.git_users.ui.profile_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.example.git_users.ui.profile_screen.contract.FollowersUiState
import com.example.git_users.ui.profile_screen.contract.ProfileScreenUiState
import com.example.git_users.ui.profile_screen.stateholder.ProfileScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    login: String,
    showDismissSnackbar: @Composable (String) -> Unit,
    connectionIsOnline: Boolean
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    LargeText(text = login)
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                navigationIcon = { BackTopAppBarButton(navController = navController) }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        if (!connectionIsOnline) {
            showDismissSnackbar.invoke(stringResource(id = R.string.no_network))
        } else {
            val viewModel: ProfileScreenViewModel = hiltViewModel()
            viewModel.setLogin(login = login)
            val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
            when (uiState) {
                is ProfileScreenUiState.Initial -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
                is ProfileScreenUiState.ProfileInfo -> {
                    ProfileLazyGrid(
                        uiState = uiState as ProfileScreenUiState.ProfileInfo,
                        paddingValues = paddingValues,
                        navController = navController,
                        showDismissSnackbar = showDismissSnackbar,
                        connectionIsOnline = connectionIsOnline
                    )
                }
                is ProfileScreenUiState.Error -> {
                    showDismissSnackbar.invoke(
                        stringResource(
                            id = R.string.error_message,
                            (uiState as ProfileScreenUiState.Error).code,
                            (uiState as ProfileScreenUiState.Error).message
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileLazyGrid(
    uiState: ProfileScreenUiState.ProfileInfo,
    paddingValues: PaddingValues,
    navController: NavHostController,
    showDismissSnackbar: @Composable (String) -> Unit,
    connectionIsOnline: Boolean
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
    ) {
        item(span = { GridItemSpan(maxCurrentLineSpan) }) {
            ProfileLargeCard(
                profile = uiState.profile,
                paddingValues = paddingValues,
                showDismissSnackbar = showDismissSnackbar
            )
        }
        when (val followersState = uiState.followersUiState) {
            is FollowersUiState.Error -> {
                item {
                    showDismissSnackbar.invoke(
                        stringResource(
                            id = R.string.error_message,
                            followersState.code,
                            followersState.message
                        )
                    )
                }
            }
            is FollowersUiState.FollowersList -> {
                if (followersState.followersList.isNotEmpty()) {
                    item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                        LargeText(
                            text = stringResource(id = R.string.followers),
                            modifier = Modifier.padding(
                                start = 32.dp,
                                end = 32.dp,
                                bottom = 8.dp
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                    items(followersState.followersList) { profile ->
                        ProfileSmallCard(
                            profile = profile,
                            connectionIsOnline = connectionIsOnline,
                            onClick = {
                                navController.navigate("profile_screen/profile=${profile.login}")
                            }
                        )
                    }
                }
            }
            is FollowersUiState.Initial -> {
                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {

}