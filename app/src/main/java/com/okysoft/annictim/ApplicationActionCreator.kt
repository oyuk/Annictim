package com.okysoft.annictim

import android.util.Log
import com.okysoft.annictim.api.repository.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ApplicationActionCreator @Inject constructor(
        private val repository: UserRepository,
        private val dispatcher: ApplicationDispatcher) {

    fun getMe() {
        GlobalScope.launch {
            try {
                val user = repository.getMe().await()
                dispatcher.dispatch(ApplicationAction.GetMe(user))
            } catch (t: Throwable) {
                Log.d("", t.toString())
            }
        }
    }

}