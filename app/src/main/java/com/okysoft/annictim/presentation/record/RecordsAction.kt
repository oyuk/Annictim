package com.okysoft.annictim.presentation.record

import com.okysoft.annictim.infra.api.model.response.RecordResponse

sealed class RecordsAction {
    class StartFetch: RecordsAction()
    class Success(val items: List<RecordResponse>): RecordsAction()
    class Error(val error: Throwable): RecordsAction()
}