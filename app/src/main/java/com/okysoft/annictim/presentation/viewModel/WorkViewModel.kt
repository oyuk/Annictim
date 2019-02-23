package com.okysoft.annictim.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.api.model.WorkRequestParams
import com.okysoft.annictim.api.model.request.WorkStatusRequestParams
import com.okysoft.annictim.api.model.response.Cast
import com.okysoft.annictim.api.model.response.Staff
import com.okysoft.annictim.api.model.response.Work
import com.okysoft.annictim.api.repository.CastRepository
import com.okysoft.annictim.api.repository.MeRepository
import com.okysoft.annictim.api.repository.StaffRepository
import com.okysoft.annictim.api.repository.WorkRepository
import com.okysoft.annictim.presentation.CastRequestParams
import com.okysoft.annictim.presentation.StaffRequestParams
import com.okysoft.annictim.presentation.WatchKind
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WorkViewModel constructor(
    workRepository: WorkRepository,
    castRepository: CastRepository,
    staffRepository: StaffRepository,
    private val meRepository: MeRepository,
    workId: Int,
    coroutineContext: CoroutineContext
) : ViewModel() {

    class Factory @Inject constructor(
        private val workRepository: WorkRepository,
        private val castRepository: CastRepository,
        private val staffRepository: StaffRepository,
        private val meRepository: MeRepository,
        private val workId: Int,
        private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorkViewModel(workRepository,
                castRepository,
                staffRepository,
                meRepository,
                workId,
                coroutineContext) as T
        }
    }

    private val _work = MutableLiveData<Work>()
    val work: LiveData<Work> = _work
    private val _casts = MutableLiveData<List<Cast>>()
    val casts: LiveData<List<Cast>> = _casts
    private val _staffs = MutableLiveData<List<Staff>>()
    val staffs: LiveData<List<Staff>> = _staffs
    private val _workKind = MutableLiveData<WatchKind>()
    val workKind: LiveData<WatchKind> = _workKind
    private val job = Job()
    private val coroutineContext = coroutineContext + job

    init {

//        workRepository.me(WorkRequestParams(ids = listOf(workId),
//            type = WorkRequestParams.Type.Me,
//            perPage = 1,
//            season = null))
//            .filterSuccess()
//            .filter { it.isNullOrEmpty() }
//            .map { it.first().status?.kind }
//            .filter { it.isNotBlank()  }
//            .map { WatchKind.fromString(it) }
//            .subscribeBy {
//                _workKind.postValue(it)
//            }
//            .addTo(compositeDisposable)

        GlobalScope.launch(coroutineContext) {
            try {
                val response = workRepository.get(WorkRequestParams(
                    fields = WorkRequestParams.Fields.All,
                    ids = listOf(workId),
                    season = null,
                    perPage = 1
                )).await()
                _work.postValue(response.works.first())
            } catch (throwable: Throwable) {

            }
        }

        GlobalScope.launch(coroutineContext) {
            try {
                val response = castRepository.get(CastRequestParams(
                    fields = CastRequestParams.FieldType.All,
                    workId = workId,
                    perPage = 6)).await()
                _casts.postValue(response.casts)
            } catch (throwable: Throwable) {

            }
        }

        GlobalScope.launch(coroutineContext) {
            try {
                val response = staffRepository.get(StaffRequestParams(
                    fields = StaffRequestParams.FieldType.Minimum,
                    workId = workId,
                    perPage = 6)).await()
                _staffs.postValue(response.staffs)
            } catch (throwable: Throwable) {

            }
        }

    }

    fun updateStatus(workId: Int, status: String) {
        val watchKind = WatchKind.fromJA(status)
        GlobalScope.launch(coroutineContext) {
            try {
                meRepository.updateStatus(WorkStatusRequestParams(workId, watchKind))
            } catch (trowable: Throwable) {

            }
        }
    }

}