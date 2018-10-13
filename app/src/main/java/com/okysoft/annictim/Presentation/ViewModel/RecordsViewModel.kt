package com.okysoft.annictim.Presentation.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.okysoft.annictim.API.Model.Response.Record
import com.okysoft.annictim.Presentation.RecordDispatcher
import com.okysoft.annictim.toLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class RecordsViewModel @Inject constructor(
        private val episodeId: Int,
        private val dispatcher: RecordDispatcher): ViewModel() {

    class Factory @Inject constructor (
            private val episodeId: Int,
            private val dispatcher: RecordDispatcher
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RecordsViewModel(episodeId, dispatcher) as T
        }
    }

    val records: LiveData<List<Record>> = dispatcher.success
            .map { it.items }
            .observeOn(AndroidSchedulers.mainThread())
            .toLiveData()

    val show_empty = dispatcher.success
            .map { it.items }
            .filter { it.isEmpty() }
            .observeOn(AndroidSchedulers.mainThread())
            .toLiveData()

}