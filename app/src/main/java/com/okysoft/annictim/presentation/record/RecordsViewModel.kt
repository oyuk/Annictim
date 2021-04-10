package com.okysoft.annictim.presentation.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.domain.model.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(val dispatcher: RecordDispatcher): ViewModel() {

    val records: LiveData<List<Record>> = dispatcher.success
            .map { it.items }
            .observeOn(AndroidSchedulers.mainThread())
            .toLiveData()

}