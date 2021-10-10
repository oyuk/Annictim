package com.okysoft.annictim.presentation.works

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.okysoft.annictim.R

@ExperimentalPagerApi
@Composable
fun FeedWorksScreen() {
    val pages = listOf<PagerTabItem>(
        PagerTabItem(stringResource(id = R.string.current_season)),
        PagerTabItem(stringResource(id = R.string.next_season)),
        PagerTabItem(stringResource(id = R.string.previous_season))
    )
    WorksPagerTab(pages = pages, type = WorksPagerType.Feed)
}