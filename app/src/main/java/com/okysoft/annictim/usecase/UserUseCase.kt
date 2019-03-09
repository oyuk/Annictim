package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.User
import kotlinx.coroutines.Deferred

interface UserUseCase {
    fun getMe(): Deferred<User>
    fun get(userId: Int): Deferred<List<User>>
}
