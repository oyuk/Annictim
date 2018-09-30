package com.okysoft.annictim.Presentation

import com.okysoft.annictim.API.Model.Response.Record

sealed class RecordsAction {
    class StartFetch: RecordsAction()
    class Success(val items: List<Record>): RecordsAction()
    class Error(val error: Throwable): RecordsAction()
}