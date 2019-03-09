package com.okysoft.annictim.translator

import com.okysoft.annictim.domain.Record
import com.okysoft.annictim.infra.api.model.response.RecordResponse

class RecordTranslator: Translator<RecordResponse, Record> {

    override fun translate(response: RecordResponse): Record {
        return Record(
            id = response.id,
            comment = response.comment ?: "",
            ratingState = response.ratingState,
            createdAt = response.createdAt,
            user = response.user
        )
    }

}