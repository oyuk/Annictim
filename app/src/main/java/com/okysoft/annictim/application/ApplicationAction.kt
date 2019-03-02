package com.okysoft.annictim.application

import com.okysoft.annictim.infra.api.model.response.User

sealed class ApplicationAction {
    class Logout: ApplicationAction()
    class GetMe(val me: User): ApplicationAction()
}