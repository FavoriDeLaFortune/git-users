package com.example.git_users.ui.components.rows

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.git_users.ui.components.columns.ProfileExtendedInfoColumn
import com.example.git_users.ui.model.ExtendedInfoState

@Composable
fun ProfileAvatarAndInfoRow(
    avatarUrl: String,
    extendedInfoStateSuccess: ExtendedInfoState.Success,
    modifier: Modifier = Modifier
) {
    Row {
        AsyncImage(
            model = avatarUrl,
            modifier = modifier
                .fillMaxHeight()
                .width(126.dp)
                .padding(top = 16.dp, bottom = 20.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        ProfileExtendedInfoColumn(extendedInfoStateSuccess = extendedInfoStateSuccess)
    }
}