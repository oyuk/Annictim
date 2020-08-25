package com.okysoft.domain.usecase

import com.okysoft.domain.model.Episode
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface EpisodeUseCase {

    suspend fun get(workId: Int): Flow<List<Episode>>

}
