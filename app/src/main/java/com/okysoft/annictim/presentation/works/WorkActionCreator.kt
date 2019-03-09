package com.okysoft.annictim.presentation.works

import com.okysoft.annictim.infra.api.model.response.WorkResponse
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WorkActionCreator @Inject constructor(
    private val dispatcher: WorkDispatcher,
    private val coroutineContext: CoroutineContext
) {

    fun updateStatus(workResponse: WorkResponse) {
        dispatcher.dispatch(WorkAction.Update(workResponse))
    }

}