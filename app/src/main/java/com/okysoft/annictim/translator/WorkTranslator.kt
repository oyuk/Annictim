package com.okysoft.annictim.translator

import com.okysoft.annictim.domain.Work
import com.okysoft.annictim.infra.api.model.response.WorkResponse
import com.okysoft.annictim.presentation.WatchKind

class WorkTranslator: Translator<WorkResponse, Work> {

    override fun translate(response: WorkResponse): Work {
        return Work(
            id = response.id,
            title =  response.title,
            media = response.media ?: "",
            twitterUsername = response.twitterUsername,
            twitterHashtag = response.twitterHashtag,
            wikipediaUrl = response.wikipediaUrl,
            officialSiteUrl = response.officialSiteUrl,
            mediaText = response.mediaText ?: "'",
            seasonNameText = response.seasonNameText ?: "",
            episodesCount = response.episodesCount ?: 0,
            watchersCount = response.watchersCount ?: 0,
            reviewsCount = response.reviewsCount ?: 0,
            imageUrl = response.images.recommendedUrl,
            noEpisodes = false,
            watchKind = WatchKind.fromString(response.status?.kind ?: "")
        )
    }

}