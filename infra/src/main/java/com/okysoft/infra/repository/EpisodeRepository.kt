package com.okysoft.infra.repository

import com.okysoft.infra.impl.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    suspend fun get(workId: Int, order: String): Flow<List<Episode>>
}