package com.okysoft.infra

import com.okysoft.infra.response.UserResponse

sealed class ApplicationAction {
    class Logout: ApplicationAction()
    class GetMe(val me: UserResponse): ApplicationAction()
}