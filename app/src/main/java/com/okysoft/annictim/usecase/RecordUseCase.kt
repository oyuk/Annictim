package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Record
import kotlinx.coroutines.Deferred

interface RecordUseCase {

    fun get(episodeId: Int): Deferred<List<Record>>

}
