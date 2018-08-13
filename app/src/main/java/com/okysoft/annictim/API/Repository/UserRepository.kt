package com.okysoft.annictim.API.Repository

import com.okysoft.annictim.API.AnnictService
import com.okysoft.annictim.API.Model.Response.User
import com.okysoft.annictim.Result
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(private val service: AnnictService.User) {

    fun getMe(): Single<Result<User>> {
        return service.getMe()
    }

}