package com.okysoft.domain.translator

import com.okysoft.data.CastRespnse
import com.okysoft.domain.model.Cast

class CastTranslator: Translator<CastRespnse, Cast> {

    override fun translate(response: com.okysoft.data.CastRespnse): Cast {
        return Cast(
            id = response.id,
            character = response.character,
            name = response.name
        )
    }

}