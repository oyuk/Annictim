package com.okysoft.annictim.application

import com.okysoft.annictim.infra.api.model.response.UserResponse

sealed class ApplicationAction {
    class Logout: ApplicationAction()
    class GetMe(val me: UserResponse): ApplicationAction()
}