package com.okysoft.annictim.presentation.workDetail

import android.util.Log
import androidx.lifecycle.*
import com.okysoft.data.CastRequestParams
import com.okysoft.data.StaffRequestParams
import com.okysoft.data.WatchKind
import com.okysoft.data.WorkStatusRequestParams
import com.okysoft.domain.model.Cast
import com.okysoft.domain.model.Staff
import com.okysoft.domain.model.Work
import com.okysoft.domain.usecase.CastUseCase
import com.okysoft.domain.usecase.StaffUseCase
import com.okysoft.domain.usecase.WorkUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class WorkViewModel constructor(
    private val workUseCase: WorkUseCase,
    private val castUseCase: CastUseCase,
    private val staffUseCase: StaffUseCase,
    private val meRepository: com.okysoft.infra.repository.MeRepository,
    val work: Work
) : ViewModel() {

    class Factory @Inject constructor(
        private val workUseCase: WorkUseCase,
        private val castUseCase: CastUseCase,
        private val staffUseCase: StaffUseCase,
        private val meRepository: com.okysoft.infra.repository.MeRepository,
        private val work: Work
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorkViewModel(workUseCase,
                castUseCase,
                staffUseCase,
                meRepository,
                work) as T
        }
    }

    private val _casts = MutableLiveData<List<Cast>>()
    val casts: LiveData<List<Cast>> = _casts
    private val _staffs = MutableLiveData<List<Staff>>()
    val staffs: LiveData<List<Staff>> = _staffs
    private val _workKind = MutableLiveData<WatchKind>()
    val workKind: LiveData<WatchKind> = _workKind
    private val statusPublisher = PublishProcessor.create<WatchKind>()
    private val compositeDisposable = CompositeDisposable()

    init {
        viewModelScope.launch {
            try {
                workUseCase.getWatchKind(workId = work.id).collect {
                    _workKind.postValue(it)
                }
            } catch (throwable: Throwable) {
                Log.e("", throwable.toString())
            }
        }

        viewModelScope.launch {
            try {
                castUseCase.get(CastRequestParams(
                    fields = CastRequestParams.FieldType.All,
                    workId = work.id)).collect {
                    _casts.postValue(it)
                }
            } catch (throwable: Throwable) {

            }
        }

        viewModelScope.launch {
            try {
                staffUseCase.get(StaffRequestParams(
                    fields = StaffRequestParams.FieldType.Minimum,
                    workId = work.id)).collect {
                    _staffs.postValue(it)
                }
            } catch (throwable: Throwable) {

            }
        }

        statusPublisher
            .skip(1)
            .distinctUntilChanged()
            .subscribeBy {
                viewModelScope.launch {
                    try {
                        meRepository.updateStatus(WorkStatusRequestParams(work.id, it))
                    } catch (trowable: Throwable) {
                        Log.e("", trowable.toString())
                    }
                }
            }.addTo(compositeDisposable)
    }

    fun updateStatus(status: String) {
        val watchKind = WatchKind.fromJA(status)
        statusPublisher.onNext(watchKind)
    }

}