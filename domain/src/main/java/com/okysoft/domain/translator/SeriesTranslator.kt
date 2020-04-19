package com.okysoft.domain.translator

import com.okysoft.domain.model.Series
import com.okysoft.infra.response.CastResponse

class SeriesTranslator: Translator<CastResponse.CharacterResponse.SeriesResponse, Series> {
    override fun translate(response: CastResponse.CharacterResponse.SeriesResponse): Series {
        return Series(
            id = response.id,
            name = response.name,
            nameEn = response.nameEn,
            nameRo =  response.nameRo
        )
    }
}