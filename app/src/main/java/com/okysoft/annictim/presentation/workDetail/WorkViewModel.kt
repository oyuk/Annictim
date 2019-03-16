package com.okysoft.annictim.presentation.workDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.domain.Cast
import com.okysoft.annictim.domain.Staff
import com.okysoft.annictim.domain.Work
import com.okysoft.annictim.infra.api.model.request.CastRequestParams
import com.okysoft.annictim.infra.api.model.request.StaffRequestParams
import com.okysoft.annictim.infra.api.model.request.WorkStatusRequestParams
import com.okysoft.annictim.infra.api.repository.MeRepository
import com.okysoft.annictim.presentation.WatchKind
import com.okysoft.annictim.usecase.CastUseCase
import com.okysoft.annictim.usecase.StaffUseCase
import com.okysoft.annictim.usecase.WorkUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WorkViewModel constructor(
    workUseCase: WorkUseCase,
    castUseCase: CastUseCase,
    staffUseCase: StaffUseCase,
    private val meRepository: MeRepository,
    work: Work,
    private val context: CoroutineContext
) : ViewModel(), CoroutineScope {

    class Factory @Inject constructor(
        private val workUseCase: WorkUseCase,
        private val castUseCase: CastUseCase,
        private val staffUseCase: StaffUseCase,
        private val meRepository: MeRepository,
        private val work: Work,
        private val context: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorkViewModel(workUseCase,
                castUseCase,
                staffUseCase,
                meRepository,
                work,
                context) as T
        }
    }

    private val _work = MutableLiveData<Work>()
    val workResponse: LiveData<Work> = _work
    private val _casts = MutableLiveData<List<Cast>>()
    val casts: LiveData<List<Cast>> = _casts
    private val _staffs = MutableLiveData<List<Staff>>()
    val staffs: LiveData<List<Staff>> = _staffs
    private val _workKind = MutableLiveData<WatchKind>()
    val workKind: LiveData<WatchKind> = _workKind
    private val statusPublisher = PublishProcessor.create<WatchKind>()
    private val job = Job()
    private val compositeDisposable = CompositeDisposable()
    override val coroutineContext: CoroutineContext
        get() = context + job

    init {
        _work.postValue(work)

        launch {
            try {
                val watchKind = workUseCase.getWatchKind(workId = work.id).await()
                _workKind.postValue(watchKind)
            } catch (throwable: Throwable) {
                Log.e("", throwable.toString())
            }
        }

        launch {
            try {
                val response = castUseCase.get(CastRequestParams(
                    fields = CastRequestParams.FieldType.All,
                    workId = work.id)).await()
                _casts.postValue(response)
            } catch (throwable: Throwable) {

            }
        }

        launch {
            try {
                val response = staffUseCase.get(StaffRequestParams(
                    fields = StaffRequestParams.FieldType.Minimum,
                    workId = work.id)).await()
                _staffs.postValue(response)
            } catch (throwable: Throwable) {

            }
        }

        statusPublisher
            .skip(1)
            .distinctUntilChanged()
            .subscribeBy {
                launch {
                    try {
                        val response = meRepository.updateStatus(WorkStatusRequestParams(work.id, it)).await()
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