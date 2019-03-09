package com.okysoft.annictim.presentation.workDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.domain.Cast
import com.okysoft.annictim.domain.Work
import com.okysoft.annictim.infra.api.model.request.CastRequestParams
import com.okysoft.annictim.infra.api.model.request.StaffRequestParams
import com.okysoft.annictim.infra.api.model.request.WorkRequestParams
import com.okysoft.annictim.infra.api.model.request.WorkStatusRequestParams
import com.okysoft.annictim.infra.api.model.response.StaffResponse
import com.okysoft.annictim.infra.api.repository.MeRepository
import com.okysoft.annictim.infra.api.repository.StaffRepository
import com.okysoft.annictim.presentation.WatchKind
import com.okysoft.annictim.usecase.CastUseCase
import com.okysoft.annictim.usecase.WorkUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx2.await
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WorkViewModel constructor(
    workUseCase: WorkUseCase,
    castUseCase: CastUseCase,
    staffRepository: StaffRepository,
    private val meRepository: MeRepository,
    work: Work,
    coroutineContext: CoroutineContext
) : ViewModel() {

    class Factory @Inject constructor(
        private val workUseCase: WorkUseCase,
        private val castUseCase: CastUseCase,
        private val staffRepository: StaffRepository,
        private val meRepository: MeRepository,
        private val work: Work,
        private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorkViewModel(workUseCase,
                castUseCase,
                staffRepository,
                meRepository,
                work,
                coroutineContext) as T
        }
    }

    private val _work = MutableLiveData<Work>()
    val workResponse: LiveData<Work> = _work
    private val _casts = MutableLiveData<List<Cast>>()
    val casts: LiveData<List<Cast>> = _casts
    private val _staffs = MutableLiveData<List<StaffResponse>>()
    val staffs: LiveData<List<StaffResponse>> = _staffs
    private val _workKind = MutableLiveData<WatchKind>()
    val workKind: LiveData<WatchKind> = _workKind
    private val statusPublisher = PublishProcessor.create<WatchKind>()
    private val job = Job()
    private val coroutineContext = coroutineContext + job
    private val compositeDisposable = CompositeDisposable()

    init {
        _work.postValue(work)

        GlobalScope.launch(coroutineContext) {
            try {
                val response = workUseCase.me(WorkRequestParams(
                    type = WorkRequestParams.Type.Me,
                    fields = WorkRequestParams.Fields.Status,
                    ids = listOf(work.id),
                    season = null,
                    perPage = 1
                )).await()
                val watchKind = response.firstOrNull()?.watchKind ?: WatchKind.no_select
                _workKind.postValue(watchKind)
            } catch (throwable: Throwable) {

            }
        }

        GlobalScope.launch(coroutineContext) {
            try {
                val response = castUseCase.get(CastRequestParams(
                    fields = CastRequestParams.FieldType.All,
                    workId = work.id)).await()
                _casts.postValue(response)
            } catch (throwable: Throwable) {

            }
        }

        GlobalScope.launch(coroutineContext) {
            try {
                val response = staffRepository.get(StaffRequestParams(
                    fields = StaffRequestParams.FieldType.Minimum,
                    workId = work.id)).await()
                _staffs.postValue(response.staffs)
            } catch (throwable: Throwable) {

            }
        }

        statusPublisher
            .skip(1)
            .distinctUntilChanged()
            .subscribeBy {
                GlobalScope.launch(coroutineContext) {
                    try {
                        val response = meRepository.updateStatus(WorkStatusRequestParams(work.id, it)).await()
                    } catch (trowable: Throwable) {

                    }
                }
            }.addTo(compositeDisposable)
    }

    fun updateStatus(status: String) {
        val watchKind = WatchKind.fromJA(status)
        statusPublisher.onNext(watchKind)
    }

}