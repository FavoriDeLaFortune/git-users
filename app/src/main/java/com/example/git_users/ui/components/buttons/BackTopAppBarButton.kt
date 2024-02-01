package com.example.git_users.ui.components.buttons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.git_users.R

@Composable
fun BackTopAppBarButton(navController: NavHostController) {
    IconButton(onClick = { navController.popBackStack() }) {
        Icon(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = null
        )
    }
}