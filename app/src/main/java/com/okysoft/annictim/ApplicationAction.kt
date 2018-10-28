package com.okysoft.annictim

import com.okysoft.annictim.API.Model.Response.User

sealed class ApplicationAction {
    class Logout: ApplicationAction()
    class GetMe(val me: User): ApplicationAction()
}