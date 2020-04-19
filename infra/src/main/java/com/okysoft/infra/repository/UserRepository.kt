package com.okysoft.infra.repository

import com.okysoft.infra.response.UserResponse
import com.okysoft.infra.response.UsersResponse
import com.okysoft.infra.AnnictService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val service: AnnictService.User,
    private val authRepository: AuthRepository
        ) {

    suspend fun getMe(): UserResponse {
        return service.getMe(authRepository.getStoredAccessToken())
    }

    suspend fun get(userId: Int): UsersResponse {
        return service.get("${userId}")
    }

}