package com.okysoft.domain.usecase

import com.okysoft.domain.model.Record
import kotlinx.coroutines.Deferred

interface RecordUseCase {

    fun get(episodeId: Int): Deferred<List<Record>>

}
