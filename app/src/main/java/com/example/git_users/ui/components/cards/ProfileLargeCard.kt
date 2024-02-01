package com.example.git_users.ui.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.git_users.ui.components.texts.LargeText
import com.example.git_users.ui.model.Profile


@Composable
fun ProfileLargeCard(profile: Profile, topBarPaddingValues: PaddingValues) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 24.dp)
            .padding(topBarPaddingValues)
            .clip(RoundedCornerShape(24.dp))
    ) {
        val paddingModifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .align(Alignment.CenterHorizontally)
        Row {
            AsyncImage(
                model = profile.avatarUrl,
                modifier = paddingModifier
                    .fillMaxHeight()
                    .width(126.dp)
                    .padding(top = 16.dp, bottom = 20.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column {
                Text(
                    text = "${profile.name}",
                    modifier = Modifier.padding(bottom = 8.dp, top = 16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${profile.company}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${profile.email}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${profile.blog}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${profile.location}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Text(
            text = "Информация",
            modifier = paddingModifier.padding(top = 20.dp, bottom = 8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "${profile.bio}",
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileLargeCardPreview() {
    ProfileLargeCard(
        Profile(
            login = "123",
            avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4"
        ),
        PaddingValues(12.dp)
    )
}