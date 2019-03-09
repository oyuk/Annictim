package com.okysoft.annictim.presentation.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.infra.api.model.response.Record
import com.okysoft.annictim.extension.toLiveData
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