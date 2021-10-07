package com.okysoft.annictim.presentation.episode

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.okysoft.domain.model.Episode

private class PreviewEpisodeListProvider : PreviewParameterProvider<List<Episode>> {
    override val values: Sequence<List<Episode>>
        get() = sequenceOf(listOf(
            Episode(1, "1", "title1"),
            Episode(2, "2", "title2")
        ))
}

@Preview
@Composable
fun EpisodeList(
    @PreviewParameter(PreviewEpisodeListProvider::class) episodes: List<Episode>,
    onClick: (Episode) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(
            items = episodes,
            itemContent = { item ->
                MaterialTheme {
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier.clickable { onClick(item) }
                            .height(48.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = item.fullTitle,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                        )
                    }
                    Divider(
                        color = Color(0x19000000),
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        )
    }
}