package com.okysoft.domain.usecase

import com.okysoft.domain.model.User
import kotlinx.coroutines.Deferred

interface UserUseCase {
    suspend fun getMe(): User
    suspend fun get(userId: Int): List<User>
}
