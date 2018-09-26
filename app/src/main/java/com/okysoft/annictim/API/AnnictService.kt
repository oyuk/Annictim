package com.okysoft.annictim.API

import com.okysoft.annictim.API.Model.Request.OauthRequestModel
import com.okysoft.annictim.API.Model.Response.EpisodesResponse
import com.okysoft.annictim.API.Model.Response.OauthResponseModel
import com.okysoft.annictim.API.Model.Response.WorksResponse
import com.okysoft.annictim.Result
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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
        // Apolloを使う場合はこちら
        fun latest(season: String): Single<Result<List<com.okysoft.annictim.API.Model.Response.Work>>>
        @GET("/v1/works")
        fun _latest(@Query("filter_season") season: String,
                    @Query("fields") fields: String?
                    ): Single<WorksResponse>

        interface Me {

            @GET("/v1/me/works")
            fun me(
                    @Query("filter_status") filterStatus: String,
                    @Query("page") page: Int
            ): Single<WorksResponse>

        }

        @GET("/v1/works")
        fun get(@Query("filter_ids") filterIds: String): Single<WorksResponse>
    }

    interface Episode {
        @GET("/v1/episodes")
        fun get(
                @Query("filter_work_id") workId: Int
        ): Single<EpisodesResponse>
    }

    interface Review {

    }

    interface Works: Work, Work.Me

}