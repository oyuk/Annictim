package com.okysoft.annictim.API

import com.okysoft.annictim.API.Model.Request.OauthRequestModel
import com.okysoft.annictim.API.Model.Response.OauthResponseModel
import com.okysoft.annictim.Result
import io.reactivex.Single
import retrofit2.http.POST

interface AnnictService {

    interface Oauth {

        @POST("/oauth/token")
        fun getAccessToken(
                @retrofit2.http.Body oauthRequestModel: OauthRequestModel
        ): Single<OauthResponseModel>

    }

    interface User {
        fun getMe(): Single<Result<com.okysoft.annictim.API.Model.Response.User>>
    }

    interface Work {
        fun latest(): Single<Result<List<com.okysoft.annictim.API.Model.Response.Work>>>
    }

}