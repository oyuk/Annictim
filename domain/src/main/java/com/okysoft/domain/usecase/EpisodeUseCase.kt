package com.okysoft.domain.usecase

import com.okysoft.domain.model.Episode
import kotlinx.coroutines.Deferred

interface EpisodeUseCase {

    suspend fun get(workId: Int): List<Episode>

}
