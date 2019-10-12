package com.okysoft.domain.usecase

import com.okysoft.domain.model.User
import kotlinx.coroutines.Deferred

interface UserUseCase {
    fun getMe(): Deferred<User>
    fun get(userId: Int): Deferred<List<User>>
}
