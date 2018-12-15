package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.User
import com.okysoft.annictim.AuthRepository
import com.okysoft.annictim.Result
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(
        private val service: AnnictService.User,
        private val authRepository: AuthRepository
        ) {

    fun getMe(): Single<Result<User>> {
        return service.getMe(authRepository.getStoredAccessToken())
                .map { Result.success(it) }
                .onErrorReturn { Result.failure<User>(it.toString(), it) }
                .subscribeOn(Schedulers.newThread())
    }

    fun get(userId: Int): Single<Result<User>> {
        return service.get("${userId}")
                .map { Result.success(it.users.first()) }
                .onErrorReturn { Result.failure<User>(it.toString(), it) }
                .subscribeOn(Schedulers.newThread())
    }

}