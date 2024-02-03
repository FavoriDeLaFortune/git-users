package com.example.git_users.ui.components.texts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.git_users.R

@Composable
fun ProfileLargeText(
    text: String?,
    exceptionText: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    if (text.isNullOrBlank()) {
        LargeText(
            text = stringResource(id = R.string.no_information_placement, exceptionText),
            modifier = modifier,
            textAlign = textAlign
        )
    } else {
        LargeText(
            text = text,
            modifier = modifier,
            textAlign = textAlign
        )
    }
}