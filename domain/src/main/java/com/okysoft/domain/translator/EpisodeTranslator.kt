package com.okysoft.domain.translator

import com.okysoft.data.EpisodeResponse
import com.okysoft.domain.model.Episode

class EpisodeTranslator: Translator<EpisodeResponse, Episode> {

    override fun translate(response: EpisodeResponse): Episode {
        return Episode(
            id = response.id,
            numberText = response.numberText,
            title = response.title ?: ""
        )
    }

}