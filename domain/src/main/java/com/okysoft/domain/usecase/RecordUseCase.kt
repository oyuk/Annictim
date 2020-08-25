package com.okysoft.domain.usecase

import com.okysoft.domain.model.Record
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface RecordUseCase {

    suspend fun get(episodeId: Int): Flow<List<Record>>

}
