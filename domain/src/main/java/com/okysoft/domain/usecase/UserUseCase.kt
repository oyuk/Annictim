package com.okysoft.domain.usecase

import com.okysoft.domain.model.User
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    suspend fun getMe(): Flow<User>
    suspend fun get(userId: Int): Flow<List<User>>
}
