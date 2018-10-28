package com.okysoft.annictim

import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.FlowableProcessor

class ApplicationDispatcher {

    private val _logout: FlowableProcessor<ApplicationAction.Logout>
            = BehaviorProcessor.create<ApplicationAction.Logout>().toSerialized()
    val logout: Flowable<ApplicationAction.Logout> = _logout

    private val _getMe: FlowableProcessor<ApplicationAction.GetMe>
            = BehaviorProcessor.create<ApplicationAction.GetMe>().toSerialized()
    val getMe: Flowable<ApplicationAction.GetMe> = _getMe

    fun logout() {
        dispatch(ApplicationAction.Logout())
    }

    fun dispatch(action: ApplicationAction) {
        when (action) {
            is ApplicationAction.Logout -> {
                _logout.onNext(action)
            }
            is ApplicationAction.GetMe -> {
                _getMe.onNext(action)
            }
        }
    }

}