package com.okysoft.annictim.presentation.record

import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.FlowableProcessor
import javax.inject.Inject

class RecordDispatcher @Inject constructor() {

    private val _startFetch: FlowableProcessor<RecordsAction.StartFetch>
            = BehaviorProcessor.create<RecordsAction.StartFetch>().toSerialized()

    private val _success: FlowableProcessor<RecordsAction.Success>
            = BehaviorProcessor.create<RecordsAction.Success>().toSerialized()
    val success: Flowable<RecordsAction.Success> = _success

    fun dispatch(action: RecordsAction) {
        when (action) {
            is RecordsAction.StartFetch -> {
                _startFetch.onNext(action)
            }
            is RecordsAction.Success -> {
                _success.onNext(action)
            }
            is RecordsAction.Error -> {

            }
        }
    }

}