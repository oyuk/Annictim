package com.okysoft.annictim.presentation.works

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.okysoft.data.WatchKind

@ExperimentalPagerApi
@Composable
fun MeWorksScreen() {
    val pages = WatchKind.values().dropLast(1).map { PagerTabItem(it.toJA()) }
    WorksPagerTab(pages = pages, type = WorksPagerType.Me)
}