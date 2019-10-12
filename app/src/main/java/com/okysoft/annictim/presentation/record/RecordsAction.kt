package com.okysoft.annictim.presentation.record

import com.okysoft.domain.model.Record


sealed class RecordsAction {
    class StartFetch: RecordsAction()
    class Success(val items: List<Record>): RecordsAction()
    class Error(val error: Throwable): RecordsAction()
}