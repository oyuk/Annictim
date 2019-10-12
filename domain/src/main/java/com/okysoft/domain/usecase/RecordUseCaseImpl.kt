package com.okysoft.domain.usecase

import com.okysoft.domain.model.Record
import com.okysoft.domain.translator.RecordTranslator
import com.okysoft.infra.repository.RecordRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class RecordUseCaseImpl (
    private val repository: RecordRepository,
    private val translator: RecordTranslator
): RecordUseCase {

    override fun get(episodeId: Int): Deferred<List<Record>> {
        return GlobalScope.async {
            val response = repository.get(episodeId).await()
            val models = response.records.map { translator.translate(it) }
            return@async models
        }
    }

}