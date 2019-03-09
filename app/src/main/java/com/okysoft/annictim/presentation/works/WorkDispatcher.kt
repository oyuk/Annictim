package com.okysoft.annictim.presentation.works

import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.FlowableProcessor

class WorkDispatcher {

    private val _update: FlowableProcessor<WorkAction.Update>
        = BehaviorProcessor.create<WorkAction.Update>().toSerialized()
    val updateString: Flowable<WorkAction.Update> = _update


    fun dispatch(action: WorkAction) {
        when (action) {
            is WorkAction.Update -> {
                _update.onNext(action)
            }
        }
    }

}