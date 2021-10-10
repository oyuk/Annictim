package com.okysoft.annictim.presentation.works

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.okysoft.data.WorkRequestParams

@Composable
fun worksViewModelProvider(
    context: Context = checkNotNull(LocalContext.current),
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current),
    key: String?,
    requestParams: WorkRequestParams): WorksViewModel {
    val factory = WorksViewModel.provideFactory(context, requestParams)
    return key?.let {
        ViewModelProvider(viewModelStoreOwner, factory).get(key, WorksViewModel::class.java)
    } ?: ViewModelProvider(viewModelStoreOwner, factory).get(WorksViewModel::class.java)
}
