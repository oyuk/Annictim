package com.okysoft.annictim.presentation.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.okysoft.annictim.R

private class PreviewSettingItemListProvider : PreviewParameterProvider<List<SettingListSection>> {
    override val values: Sequence<List<SettingListSection>>
        get() = sequenceOf(listOf(
            SettingListSection("設定", listOf(
                SettingListItem("ログアウト", {})
            )),
            SettingListSection("フィードバック", listOf(
                SettingListItem("作者サイトへ", {})
            )),
            SettingListSection("ライセンス", listOf(
                SettingListItem("ライセンス", {})
            ))
        ))
}

@Preview(showBackground = true)
@Composable
fun SettingItemList(
    @PreviewParameter(PreviewSettingItemListProvider::class) sections: List<SettingListSection>) {
    Column(
        Modifier.padding(top = 16.dp)
    ) {
        sections.forEach { section ->
            Box(contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .height(50.dp)
                    .fillMaxWidth()) {
                Text(
                    text = section.title,
                    fontSize = 14.sp,
                    color = colorResource(R.color.colorAccent),
                    lineHeight = 50.sp
                )
            }
            section.items.forEach { item ->
                MaterialTheme {
                    Box(contentAlignment = Alignment.CenterStart,
                        modifier = Modifier
                            .clickable(onClick = item.action)
                            .padding(start = 16.dp, end = 16.dp)
                            .height(56.dp)
                            .fillMaxWidth()) {
                        Text(
                            text = item.title,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}