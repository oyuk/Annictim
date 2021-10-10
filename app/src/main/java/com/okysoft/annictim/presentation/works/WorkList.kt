package com.okysoft.annictim.presentation.works

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.okysoft.annictim.util.compose.Center
import com.okysoft.annictim.util.compose.LoadingItem
import com.okysoft.annictim.util.compose.Progress

@Composable
fun WorkList(viewModel: WorksViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val lazyList = viewModel.works.collectAsLazyPagingItems()
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(
            items = lazyList,
            key = { item -> item.annictId }) { item ->
            if (item != null) {
                WorkListCard(workFeed = item, onClick = {

                })
            }
        }
        lazyList.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { Center(modifier = Modifier.fillParentMaxSize()) { Progress() } }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {

                }
                loadState.append is LoadState.Error -> {

                }
            }
        }
    }
}