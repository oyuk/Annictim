package com.okysoft.annictim.presentation.record

import com.okysoft.annictim.infra.api.repository.RecordRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RecordsActionCreator @Inject constructor(
    private val repository: RecordRepository,
    private val dispatcher: RecordDispatcher,
    private val coroutineContext: CoroutineContext
) {

    fun fetch(episodeId: Int) {
        GlobalScope.launch(coroutineContext) {
            try {
                val response = repository.get(episodeId).await()
                dispatcher.dispatch(RecordsAction.Success(response.records))
            } catch (throwable: Throwable) {

            }
        }
    }

}