package com.okysoft.annictim.presentation.works

import com.okysoft.annictim.domain.Work

sealed class WorkAction {
    class Update(val work: Work): WorkAction()
}