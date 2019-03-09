package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Record
import com.okysoft.annictim.infra.api.repository.RecordRepository
import com.okysoft.annictim.translator.RecordTranslator
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