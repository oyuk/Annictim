package com.okysoft.infra

import android.util.Log
import com.okysoft.infra.repository.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ApplicationActionCreator @Inject constructor(
    private val repository: UserRepository,
    private val dispatcher: ApplicationDispatcher) {

    fun getMe() {
        GlobalScope.launch {
            try {
                repository.getMe().collect {
                    dispatcher.dispatch(ApplicationAction.GetMe(it))
                }
            } catch (t: Throwable) {
                Log.d("", t.toString())
            }
        }
    }

}