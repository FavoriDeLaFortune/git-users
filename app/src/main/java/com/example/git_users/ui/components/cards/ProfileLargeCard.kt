package com.example.git_users.ui.components.cards

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.git_users.R
import com.example.git_users.ui.components.columns.ProfileExtendedInfoColumn
import com.example.git_users.ui.components.rows.ProfileAvatarAndInfoRow
import com.example.git_users.ui.components.texts.LargeText
import com.example.git_users.ui.components.texts.ProfileMediumText
import com.example.git_users.ui.model.ExtendedInfoState
import com.example.git_users.ui.model.Profile


@Composable
fun ProfileLargeCard(
    profile: Profile,
    paddingValues: PaddingValues,
    showDismissSnackbar: @Composable (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 24.dp)
            .padding(paddingValues)
            .clip(RoundedCornerShape(24.dp))
    ) {
        val paddingModifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .align(Alignment.CenterHorizontally)
        if (profile.extendedInfo != null) {
            when (profile.extendedInfo) {
                is ExtendedInfoState.Success -> {
                    ProfileAvatarAndInfoRow(
                        avatarUrl = profile.avatarUrl,
                        extendedInfoStateSuccess = profile.extendedInfo,
                        modifier = paddingModifier
                    )
                    LargeText(
                        text = stringResource(id = R.string.information),
                        modifier = paddingModifier.padding(top = 20.dp, bottom = 8.dp)
                    )
                    ProfileMediumText(
                        text = profile.extendedInfo.bio,
                        exceptionText = stringResource(id = R.string.information),
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                }
                is ExtendedInfoState.Error -> {
                    showDismissSnackbar.invoke(
                        stringResource(
                            id = R.string.error_message,
                            profile.extendedInfo.code,
                            profile.extendedInfo.message
                        )
                    )
                }
            }
        } else {
            CircularProgressIndicator()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileLargeCardPreview() {

}