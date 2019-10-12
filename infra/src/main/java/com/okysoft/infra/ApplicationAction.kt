package com.okysoft.infra

import com.okysoft.data.UserResponse

sealed class ApplicationAction {
    class Logout: ApplicationAction()
    class GetMe(val me: UserResponse): ApplicationAction()
}