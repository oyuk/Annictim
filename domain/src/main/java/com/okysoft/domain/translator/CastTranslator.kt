package com.okysoft.domain.translator

import com.okysoft.domain.model.Series
import com.okysoft.infra.response.CastResponse
import com.okysoft.domain.model.Cast
import com.okysoft.domain.model.Character

class CastTranslator(private val characterTranslator: CharacterTranslator): Translator<CastResponse, Cast> {

    override fun translate(response: CastResponse): Cast {
        return Cast(
            id = response.id,
            character = characterTranslator.translate(response.character),
            name = response.name,
            annictId = 0
        )
    }

}