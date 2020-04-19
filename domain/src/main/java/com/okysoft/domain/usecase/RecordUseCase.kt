package com.okysoft.domain.usecase

import com.okysoft.domain.model.Record
import kotlinx.coroutines.Deferred

interface RecordUseCase {

    suspend fun get(episodeId: Int): List<Record>

}
