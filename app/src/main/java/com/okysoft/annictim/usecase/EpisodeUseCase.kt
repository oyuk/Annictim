package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Episode
import io.reactivex.Single

interface EpisodeUseCase {

    fun get(workId: Int): Single<List<Episode>>

}
