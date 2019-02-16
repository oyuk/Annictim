package com.okysoft.annictim.api.repository

import com.okysoft.annictim.AuthRepository
import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.User
import com.okysoft.annictim.api.model.response.UsersResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val service: AnnictService.User,
        private val authRepository: AuthRepository
        ) {

    fun getMe(): Deferred<User> {
        return service.getMe(authRepository.getStoredAccessToken())
    }

    fun get(userId: Int): Deferred<UsersResponse> {
        return service.get("${userId}")
    }

}