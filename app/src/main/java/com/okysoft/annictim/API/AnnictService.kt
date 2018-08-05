package com.okysoft.annictim.API

import com.okysoft.annictim.API.Model.Request.OauthRequestModel
import com.okysoft.annictim.API.Model.Response.OauthResponseModel
import retrofit2.Call
import retrofit2.http.POST

interface AnnictService {

    interface Oauth {

        @POST("/oauth/token")
        fun getAccessToken(
                @retrofit2.http.Body oauthRequestModel: OauthRequestModel
        ): Call<OauthResponseModel>

    }

}