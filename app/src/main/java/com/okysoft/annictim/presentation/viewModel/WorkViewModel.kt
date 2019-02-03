package com.okysoft.annictim.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.okysoft.annictim.api.model.response.Cast
import com.okysoft.annictim.api.model.response.Staff
import com.okysoft.annictim.api.model.response.Work
import com.okysoft.annictim.api.repository.CastRepository
import com.okysoft.annictim.api.repository.StaffRepository
import com.okysoft.annictim.api.repository.WorkRepository
import com.okysoft.annictim.extension.filterSuccess
import com.okysoft.annictim.presentation.CastRequestParams
import com.okysoft.annictim.presentation.StaffRequestParams
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class WorkViewModel constructor(
    private val workRepository: WorkRepository,
    private val castRepository: CastRepository,
    private val staffRepository: StaffRepository,
    private val work: Work
) : ViewModel() {

    class Factory @Inject constructor(
        private val workRepository: WorkRepository,
        private val castRepository: CastRepository,
        private val staffRepository: StaffRepository,
        private val work: Work
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorkViewModel(workRepository, castRepository, staffRepository, work) as T
        }
    }

    private val _work = MutableLiveData<Work>()
    val workLiveData: LiveData<Work> = _work
    private val _casts = MutableLiveData<List<Cast>>()
    val casts: LiveData<List<Cast>> = _casts
    private val _staffs = MutableLiveData<List<Staff>>()
    val staffs: LiveData<List<Staff>> = _staffs
    private val compositeDisposable = CompositeDisposable()

    init {
        castRepository.get(CastRequestParams(
            fields = CastRequestParams.FieldType.All,
            workId = work.id,
            perPage = 6))
            .filterSuccess()
            .subscribeBy {
                _casts.postValue(it)
            }
            .addTo(compositeDisposable)

        staffRepository.get(StaffRequestParams(
            fields = StaffRequestParams.FieldType.Minimum,
            workId = work.id))
            .filterSuccess()
            .subscribeBy {
                _staffs.postValue(it)
            }
            .addTo(compositeDisposable)

        _work.postValue(work)
    }


}