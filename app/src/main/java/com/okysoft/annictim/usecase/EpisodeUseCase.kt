package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Episode
import kotlinx.coroutines.Deferred

interface EpisodeUseCase {

    fun get(workId: Int): Deferred<List<Episode>>

}
