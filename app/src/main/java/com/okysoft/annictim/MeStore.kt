package com.okysoft.annictim

class MeStore(private val authRepository: AuthRepository) {

    val isLoggedIn: Boolean
        get() = authRepository.getStoredAccessToken().isNotEmpty()

}