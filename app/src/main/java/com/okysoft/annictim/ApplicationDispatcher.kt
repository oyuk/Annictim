package com.okysoft.annictim

import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.FlowableProcessor

class ApplicationDispatcher {

    private val _logout: FlowableProcessor<ApplicationAction.Logout>
            = BehaviorProcessor.create<ApplicationAction.Logout>().toSerialized()
    val logout: Flowable<ApplicationAction.Logout> = _logout

    fun logout() {
        _logout.onNext(ApplicationAction.Logout())
    }

}