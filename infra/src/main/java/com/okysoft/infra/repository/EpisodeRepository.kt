package com.okysoft.infra.repository

import com.okysoft.infra.response.EpisodesResponse
import com.okysoft.infra.AnnictService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface EpisodeRepository {
    suspend fun get(workId: Int, order: String): Flow<EpisodesResponse>
}