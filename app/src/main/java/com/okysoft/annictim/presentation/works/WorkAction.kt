package com.okysoft.annictim.presentation.works

import com.okysoft.annictim.infra.api.model.response.WorkResponse

sealed class WorkAction {
    class Update(val workResponse: WorkResponse): WorkAction()
}