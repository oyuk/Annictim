package com.okysoft.annictim

import android.util.Log
import com.okysoft.annictim.api.repository.UserRepository
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApplicationActionCreator @Inject constructor(
        private val repository: UserRepository,
        private val dispatcher: ApplicationDispatcher) {

    fun getMe() {
        repository.getMe()
                .subscribeOn(Schedulers.io())
                .subscribeBy {
                    when (it) {
                        is Result.Success -> {
                            dispatcher.dispatch(ApplicationAction.GetMe(it.data))
                        }
                        is Result.Failure -> {
                            Log.d("", it.throwable.toString())
                        }
                    }
                }
    }

}