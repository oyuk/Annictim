package com.okysoft.infra.repository

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: AnnictService.User,
    private val authRepository: AuthRepository
        ) {

    suspend fun getMe(): com.okysoft.data.UserResponse {
        return service.getMe(authRepository.getStoredAccessToken())
    }

    suspend fun get(userId: Int): com.okysoft.data.UsersResponse {
        return service.get("${userId}")
    }

}