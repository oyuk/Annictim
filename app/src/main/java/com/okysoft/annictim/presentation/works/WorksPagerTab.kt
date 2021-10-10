package com.okysoft.annictim.presentation.works

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.okysoft.data.WatchKind
import com.okysoft.data.WorkRequestParams
import com.okysoft.data.WorkTerm
import kotlinx.coroutines.launch

enum class WorksPagerType {
    Feed,
    Me
}

private fun createWorkRequestParams(position: Int, worksPagerType: WorksPagerType): WorkRequestParams {
    return when (worksPagerType){
        WorksPagerType.Feed -> {
            val workTerm = when(position) {
                0 -> WorkTerm.Current
                1 -> WorkTerm.Next
                2 -> WorkTerm.Previous
                else -> WorkTerm.Current
            }
            WorkRequestParams(season = workTerm.term())
        }
        WorksPagerType.Me -> {
            WorkRequestParams(
                type = WorkRequestParams.Type.Me,
                status = WatchKind.fromNum(position).toString(),
                fields = WorkRequestParams.Fields.Feed,
                season = null)
        }
    }
}

@Composable
private fun PageScreen(page: Int, worksPagerType: WorksPagerType) {
    val requestParams = createWorkRequestParams(page, worksPagerType)
    val key = "${worksPagerType.name}${page}"
    val viewModel = worksViewModelProvider(key = key, requestParams = requestParams)
    WorkList(viewModel)
}

@ExperimentalPagerApi
@Composable
private fun PagerTab(pages: List<PagerTabItem>, worksPagerType: WorksPagerType) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            pages.forEachIndexed { index, item ->
                Tab(
                    text = {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.subtitle1
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }
        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) {
            PageScreen(page = it, worksPagerType = worksPagerType)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun WorksPagerTab(pages: List<PagerTabItem>, type: WorksPagerType) {
    PagerTab(pages = pages, worksPagerType = type)
}