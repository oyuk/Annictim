package com.okysoft.annictim.presentation.works

import com.okysoft.domain.model.Work
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WorkActionCreator @Inject constructor(
    private val dispatcher: WorkDispatcher,
    private val coroutineContext: CoroutineContext
) {

    fun updateStatus(work: Work) {
        dispatcher.dispatch(WorkAction.Update(work))
    }

}