package com.example.git_users.ui.components.texts

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun MediumText(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Center) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = textAlign,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}