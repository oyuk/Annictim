package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.User
import io.reactivex.Single

interface UserUseCase {
    fun getMe(): Single<User>
    fun get(userId: Int): Single<List<User>>
}
