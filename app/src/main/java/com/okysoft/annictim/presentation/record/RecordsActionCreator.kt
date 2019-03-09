package com.okysoft.annictim.presentation.record

import com.okysoft.annictim.usecase.RecordUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx2.await
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
                val response = recordUseCase.get(episodeId).await()
                dispatcher.dispatch(RecordsAction.Success(response))
            } catch (throwable: Throwable) {

            }
        }
    }

}