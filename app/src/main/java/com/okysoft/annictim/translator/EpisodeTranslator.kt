package com.okysoft.annictim.translator

import com.okysoft.annictim.domain.Episode
import com.okysoft.annictim.infra.api.model.response.EpisodeResponse

class EpisodeTranslator: Translator<EpisodeResponse, Episode> {

    override fun translate(response: EpisodeResponse): Episode {
        return Episode(
            id = response.id,
            numberText = response.numberText,
            title = response.title ?: ""
        )
    }

}