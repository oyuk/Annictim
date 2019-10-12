package com.okysoft.domain.usecase

import com.okysoft.domain.model.Episode
import kotlinx.coroutines.Deferred

interface EpisodeUseCase {

    fun get(workId: Int): Deferred<List<Episode>>

}
