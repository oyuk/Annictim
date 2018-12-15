package com.okysoft.annictim

import com.okysoft.annictim.api.model.response.User

sealed class ApplicationAction {
    class Logout: ApplicationAction()
    class GetMe(val me: User): ApplicationAction()
}