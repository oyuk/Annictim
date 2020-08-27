package com.okysoft.annictim.presentation.record

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okysoft.annictim.extension.toLiveData
import com.okysoft.domain.model.Record
import io.reactivex.android.schedulers.AndroidSchedulers

class RecordsViewModel @ViewModelInject constructor(val dispatcher: RecordDispatcher): ViewModel() {

    val records: LiveData<List<Record>> = dispatcher.success
            .map { it.items }
            .observeOn(AndroidSchedulers.mainThread())
            .toLiveData()

}