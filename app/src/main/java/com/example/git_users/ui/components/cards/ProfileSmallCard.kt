package com.example.git_users.ui.components.cards


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.git_users.R
import com.example.git_users.ui.components.texts.LargeText
import com.example.git_users.ui.components.texts.MediumText
import com.example.git_users.ui.model.ExtendedInfoState
import com.example.git_users.ui.model.Profile

@SuppressLint("ResourceType")
@Composable
fun ProfileSmallCard(
    profile: Profile,
    connectionIsOnline: Boolean,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { if (connectionIsOnline) onClick?.invoke() }
            .clip(RoundedCornerShape(24.dp))
    ) {
        val paddingModifier = Modifier
            .padding(horizontal = 16.dp)
            .align(Alignment.CenterHorizontally)
        AsyncImage(
            model = profile.avatarUrl,
            modifier = paddingModifier
                .width(126.dp)
                .fillMaxHeight()
                .padding(top = 16.dp, bottom = 20.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        LargeText(text = profile.login, modifier = paddingModifier.padding(bottom = 8.dp))
        if (profile.extendedInfo != null) {
            when (profile.extendedInfo) {
                is ExtendedInfoState.Success -> {
                    MediumText(
                        text = pluralStringResource(
                            id = R.plurals.subscribers_count,
                            profile.extendedInfo.followers,
                            profile.extendedInfo.followers
                        ),
                        modifier = paddingModifier
                    )
                    MediumText(
                        text = pluralStringResource(
                            id = R.plurals.repos_count,
                            profile.extendedInfo.reposCount,
                            profile.extendedInfo.reposCount
                        ),
                        modifier = paddingModifier.padding(bottom = 16.dp)
                    )
                }
                is ExtendedInfoState.Error -> {
                    MediumText(
                        text = stringResource(
                            id = R.string.error_message,
                            profile.extendedInfo.code,
                            profile.extendedInfo.message
                        ),
                        modifier = paddingModifier.padding(bottom = 32.dp)
                    )
                }
            }
        } else {
            CircularProgressIndicator(
                modifier = paddingModifier
                    .padding(bottom = 16.dp)
                    .size(36.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileSmallCardPreview() {
}