package com.okysoft.domain.usecase

import com.okysoft.domain.model.Record
import com.okysoft.domain.translator.RecordTranslator
import com.okysoft.infra.repository.RecordRepository
import kotlinx.coroutines.*

class RecordUseCaseImpl (
    private val repository: RecordRepository,
    private val translator: RecordTranslator
): RecordUseCase {

    override suspend fun get(episodeId: Int): List<Record> {
        return withContext(Dispatchers.IO) {
            val response = repository.get(episodeId)
            val models = response.records.map { translator.translate(it) }
            return@withContext models
        }
    }

}