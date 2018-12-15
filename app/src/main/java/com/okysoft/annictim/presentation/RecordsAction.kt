package com.okysoft.annictim.presentation

import com.okysoft.annictim.api.model.response.Record

sealed class RecordsAction {
    class StartFetch: RecordsAction()
    class Success(val items: List<Record>): RecordsAction()
    class Error(val error: Throwable): RecordsAction()
}