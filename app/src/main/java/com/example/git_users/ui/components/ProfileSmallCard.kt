package com.example.git_users.ui.components


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProfileSmallCard(
    login: String,
    avatarUrl: String,
    reposCount: Int? = null,
    followersCount: Int? = null,
) {

    Card(
        modifier = Modifier.fillMaxSize()
    ) {
        val paddingModifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .align(Alignment.CenterHorizontally)
        AsyncImage(
            model = avatarUrl,
            modifier = paddingModifier
                .width(126.dp)
                .height(122.dp)
                .padding(top = 16.dp, bottom = 20.dp),
            contentDescription = null
        )
        Text(
            text = login,
            modifier = paddingModifier.padding(bottom = 8.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "$followersCount подписчиков",
            modifier = paddingModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "$reposCount репозиториев",
            modifier = paddingModifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileCardPreview() {
    ProfileSmallCard(login = "123", avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4")
}