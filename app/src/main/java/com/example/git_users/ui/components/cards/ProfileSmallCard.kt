package com.example.git_users.ui.components.cards


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.git_users.R
import com.example.git_users.ui.components.texts.MediumText

@SuppressLint("ResourceType")
@Composable
fun ProfileSmallCard(
    login: String,
    avatarUrl: String,
    reposCount: Int? = null,
    followersCount: Int? = null,
    onClick: (() -> Unit)? = null
) {

    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick?.invoke() }
            .clip(RoundedCornerShape(24.dp))
    ) {
        val paddingModifier = Modifier
            .padding(horizontal = 16.dp)
            .align(Alignment.CenterHorizontally)
        AsyncImage(
            model = avatarUrl,
            modifier = paddingModifier
                .width(126.dp)
                .fillMaxHeight()
                .padding(top = 16.dp, bottom = 20.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        MediumText(text = login, modifier = paddingModifier.padding(bottom = 8.dp))
        MediumText(
            text = pluralStringResource(id = R.plurals.subscribers_count, count = followersCount!!),
            modifier = paddingModifier
        )
        MediumText(
            text = pluralStringResource(id = R.plurals.repos_count, count = reposCount!!),
            modifier = paddingModifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileSmallCardPreview() {
    ProfileSmallCard(login = "123", avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4")
}