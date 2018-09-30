package com.okysoft.annictim.Presentation

import com.okysoft.annictim.API.Repository.RecordRepository
import com.okysoft.annictim.Result
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RecordsActionCreator @Inject constructor(
        private val repository: RecordRepository,
        private val dispatcher: RecordDispatcher) {

    fun fetch(episodeId: Int) {
        repository.get(episodeId)
                .subscribeOn(Schedulers.io())
                .subscribeBy {
                    when (it) {
                        is Result.Success -> {
                            dispatcher.dispatch(RecordsAction.Success(it.data))
                        }
                        is Result.Failure -> {

                        }
                    }
                }
    }

}