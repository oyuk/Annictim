package com.okysoft.annictim.presentation.person

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.okysoft.annictim.util.compose.Center


@Preview("empty_screen", showSystemUi = true)
@Composable
fun EmptyScreen() {
    Center {
        Text(text = "見つかりませんでした",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6
        )
    }
}
