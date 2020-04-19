package com.okysoft.domain.translator

import com.okysoft.infra.response.RecordResponse
import com.okysoft.domain.model.Record

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