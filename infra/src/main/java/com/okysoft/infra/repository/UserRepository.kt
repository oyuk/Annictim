package com.okysoft.infra.repository

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: AnnictService.User,
    private val authRepository: AuthRepository
        ) {

    fun getMe(): Deferred<com.okysoft.data.UserResponse> {
        return service.getMe(authRepository.getStoredAccessToken())
    }

    fun get(userId: Int): Deferred<com.okysoft.data.UsersResponse> {
        return service.get("${userId}")
    }

}