package com.okysoft.annictim.presentation.person

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.okysoft.annictim.util.compose.Center

@Preview(showSystemUi = true)
@Composable
fun ErrorScreen(
    message: String = "エラーが発生しました",
    resume: () -> Unit = {}
) {
    Center {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = message,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
            Button(onClick = resume,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = "リロード")
            }
        }
    }
}