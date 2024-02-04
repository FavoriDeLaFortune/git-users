package com.example.git_users.ui.start_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.git_users.R
import com.example.git_users.navigation.utils.navigateToProfileScreen
import com.example.git_users.ui.components.cards.ProfileSmallCard
import com.example.git_users.ui.components.texts.LargeText
import com.example.git_users.ui.model.Profile
import com.example.git_users.ui.start_screen.contract.StartScreenUiState
import com.example.git_users.ui.start_screen.stateholder.StartScreenViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun StartScreen(
    navController: NavHostController,
    showDismissSnackbar: @Composable (String) -> Unit,
    connectionIsOnline: Boolean,
    paddingValues: PaddingValues
) {
    if (!connectionIsOnline) {
        showDismissSnackbar.invoke(stringResource(id = R.string.no_network))
    } else {
        val viewModel: StartScreenViewModel = hiltViewModel()
        val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
        when (uiState) {
            StartScreenUiState.Initial -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
            is StartScreenUiState.Error -> {
                showDismissSnackbar.invoke(
                    stringResource(
                        id = R.string.error_message,
                        (uiState as StartScreenUiState.Error).code,
                        (uiState as StartScreenUiState.Error).message
                    )
                )
            }
            is StartScreenUiState.ProfileList -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .padding(horizontal = 32.dp, vertical = 8.dp)
                        .padding(paddingValues)
                ) {
                    items((uiState as StartScreenUiState.ProfileList).list) { profile ->
                        ProfileSmallCard(
                            profile = profile,
                            connectionIsOnline = connectionIsOnline,
                            onClick = {
                                navController.navigateToProfileScreen(profile.login)
                            }
                        )
                    }
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
        modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp)
    ) {
        items(list) { profile ->

        }
    }
}