package com.example.git_users.ui.components.columns

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.git_users.R
import com.example.git_users.ui.components.texts.ProfileLargeText
import com.example.git_users.ui.components.texts.ProfileMediumText
import com.example.git_users.ui.model.ExtendedInfoState

@Composable
fun ProfileExtendedInfoColumn(
    extendedInfoStateSuccess: ExtendedInfoState.Success
) {
    Column {
        ProfileLargeText(
            text = extendedInfoStateSuccess.name,
            exceptionText = stringResource(id = R.string.no_information_name),
            modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
        )
        ProfileMediumText(
            text = extendedInfoStateSuccess.company,
            exceptionText = stringResource(id = R.string.no_information_company)
        )
        ProfileMediumText(
            text = extendedInfoStateSuccess.email,
            exceptionText = stringResource(id = R.string.no_information_email)
        )
        ProfileMediumText(
            text = extendedInfoStateSuccess.blog,
            exceptionText = stringResource(id = R.string.no_information_blog)
        )
        ProfileMediumText(
            text = extendedInfoStateSuccess.location,
            exceptionText = stringResource(id = R.string.no_information_location)
        )
    }
}