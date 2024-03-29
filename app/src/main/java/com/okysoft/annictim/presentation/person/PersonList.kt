package com.okysoft.annictim.presentation.person

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp

private class PreviewPersonListProvider : PreviewParameterProvider<List<PersonListItem>> {
    override val values: Sequence<List<PersonListItem>>
        get() = sequenceOf(listOf(
            PersonListItem("name1", "name"),
            PersonListItem("name2", "name", linkable = true)
        ))
}

@Preview("person_list", showBackground = true)
@Composable
fun PersonList(
    @PreviewParameter(PreviewPersonListProvider::class) list: List<PersonListItem>,
    onLinkClick: (link: String) -> Unit = {}) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = list,
            itemContent = { item ->
                Text(text = item.title,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(top = 8.dp))
                if (item.linkable) {
                    Text(text = item.content,
                        style = MaterialTheme.typography.body2,
                        color = Color.Blue,
                        modifier = Modifier.padding(vertical= 8.dp).clickable {
                            onLinkClick(item.content)
                        })
                } else {
                    Text(text = item.content,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        )
    }
}