package com.okysoft.annictim.presentation.works

import com.okysoft.domain.model.Work

sealed class WorkAction {
    class Update(val work: Work): WorkAction()
}