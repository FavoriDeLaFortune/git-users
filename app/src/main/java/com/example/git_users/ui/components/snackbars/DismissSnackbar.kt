package com.example.git_users.ui.components.snackbars

import android.annotation.SuppressLint
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.git_users.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DismissSnackbar(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    message: String,
) {
    coroutineScope.launch {
        snackbarHostState.showSnackbar(
            message = message,
            withDismissAction = true,
            duration = SnackbarDuration.Long
        )
    }
}