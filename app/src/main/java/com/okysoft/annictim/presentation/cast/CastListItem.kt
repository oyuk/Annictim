package com.okysoft.annictim.presentation.cast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.okysoft.domain.model.Cast

@Composable
fun CastListItem(cast: Cast) {
    Row {
        Column {
            Text(text = cast.name)
        }
    }
}