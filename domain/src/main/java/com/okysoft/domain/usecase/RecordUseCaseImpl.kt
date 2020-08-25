package com.okysoft.domain.usecase

import com.okysoft.domain.model.Record
import com.okysoft.domain.translator.RecordTranslator
import com.okysoft.infra.repository.RecordRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecordUseCaseImpl (
    private val repository: RecordRepository,
    private val translator: RecordTranslator
): RecordUseCase {

    override suspend fun get(episodeId: Int): Flow<List<Record>> {
        return repository.get(episodeId)
            .map { response ->
                response.records.map { translator.translate(it) }
            }
    }

}