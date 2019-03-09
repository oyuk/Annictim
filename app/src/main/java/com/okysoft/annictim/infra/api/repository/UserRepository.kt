package com.okysoft.annictim.infra.api.repository

import com.okysoft.annictim.infra.api.AnnictService
import com.okysoft.annictim.infra.api.model.response.UserResponse
import com.okysoft.annictim.infra.api.model.response.UsersResponse
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val service: AnnictService.User,
        private val authRepository: AuthRepository
        ) {

    fun getMe(): Deferred<UserResponse> {
        return service.getMe(authRepository.getStoredAccessToken())
    }

    fun get(userId: Int): Deferred<UsersResponse> {
        return service.get("${userId}")
    }

}