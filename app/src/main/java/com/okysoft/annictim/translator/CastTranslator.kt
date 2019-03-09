package com.okysoft.annictim.translator

import com.okysoft.annictim.domain.Cast
import com.okysoft.annictim.domain.Work
import com.okysoft.annictim.infra.api.model.response.CastRespnse
import com.okysoft.annictim.infra.api.model.response.WorkResponse
import com.okysoft.annictim.presentation.WatchKind

class CastTranslator: Translator<CastRespnse, Cast> {

    override fun translate(response: CastRespnse): Cast {
        return Cast(
            id = response.id,
            character = response.character,
            name = response.name
        )
    }

}