package com.okysoft.annictim.presentation.record

import com.okysoft.annictim.infra.api.model.response.Record

sealed class RecordsAction {
    class StartFetch: RecordsAction()
    class Success(val items: List<Record>): RecordsAction()
    class Error(val error: Throwable): RecordsAction()
}