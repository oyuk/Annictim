package com.okysoft.annictim.API.Repository

import com.okysoft.annictim.API.AnnictService
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.Result
import io.reactivex.Single
import javax.inject.Inject

class WorkRepository @Inject constructor(private val service: AnnictService.Work) {

    fun latest(): Single<Result<List<Work>>> {
        return service.latest()
    }

}