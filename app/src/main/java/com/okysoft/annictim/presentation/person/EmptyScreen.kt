package com.okysoft.annictim.presentation.person

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.okysoft.annictim.util.compose.Center


@Preview("empty_screen", showSystemUi = true)
@Composable
fun EmptyScreen() {
    Center {
        Text(text = "見つかりませんでした",
            textAlign = TextAlign.Center,
            fontSize = 20.sp)
    }
}
