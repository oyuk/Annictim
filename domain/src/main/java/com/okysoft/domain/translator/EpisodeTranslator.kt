package com.okysoft.domain.translator

import com.okysoft.domain.model.Episode

class EpisodeTranslator: Translator<com.okysoft.infra.impl.Episode, Episode> {

    override fun translate(response: com.okysoft.infra.impl.Episode): Episode {
        return Episode(
            id = response.id,
            numberText = response.numberText ?: "",
            title = response.title ?: ""
        )
    }

}