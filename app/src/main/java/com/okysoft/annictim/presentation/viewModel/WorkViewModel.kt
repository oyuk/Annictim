package com.okysoft.annictim.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.okysoft.annictim.api.model.WorkRequestParams
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
    private val workId: Int
) : ViewModel() {

    class Factory @Inject constructor(
        private val workRepository: WorkRepository,
        private val castRepository: CastRepository,
        private val staffRepository: StaffRepository,
        private val workId: Int
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorkViewModel(workRepository, castRepository, staffRepository, workId) as T
        }
    }

    private val _work = MutableLiveData<Work>()
    val work: LiveData<Work> = _work
    private val _casts = MutableLiveData<List<Cast>>()
    val casts: LiveData<List<Cast>> = _casts
    private val _staffs = MutableLiveData<List<Staff>>()
    val staffs: LiveData<List<Staff>> = _staffs
    private val compositeDisposable = CompositeDisposable()

    init {
        workRepository.get(WorkRequestParams(
            fields = WorkRequestParams.Fields.All,
            ids = listOf(workId),
            season = null,
            perPage = 1
        ))
            .filterSuccess()
            .subscribeBy {
                _work.postValue(it.first())
            }.addTo(compositeDisposable)


        castRepository.get(CastRequestParams(
            fields = CastRequestParams.FieldType.All,
            workId = workId,
            perPage = 6))
            .filterSuccess()
            .subscribeBy {
                _casts.postValue(it)
            }
            .addTo(compositeDisposable)

        staffRepository.get(StaffRequestParams(
            fields = StaffRequestParams.FieldType.Minimum,
            workId = workId))
            .filterSuccess()
            .subscribeBy {
                _staffs.postValue(it)
            }
            .addTo(compositeDisposable)
    }


}