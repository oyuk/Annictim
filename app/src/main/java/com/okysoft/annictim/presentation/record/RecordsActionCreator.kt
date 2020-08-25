package com.okysoft.annictim.presentation.record

import com.okysoft.domain.usecase.RecordUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RecordsActionCreator @Inject constructor(
    private val recordUseCase: RecordUseCase,
    private val dispatcher: RecordDispatcher,
    private val coroutineContext: CoroutineContext
) {

    fun fetch(episodeId: Int) {
        GlobalScope.launch(coroutineContext) {
            try {
                recordUseCase.get(episodeId).collect {
                    dispatcher.dispatch(RecordsAction.Success(it))
                }
            } catch (throwable: Throwable) {

            }
        }
    }

}