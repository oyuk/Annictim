package com.okysoft.annictim.api

import com.okysoft.annictim.Result
import com.okysoft.annictim.api.model.request.OauthRequestModel
import com.okysoft.annictim.api.model.response.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface AnnictService {

    interface Oauth {

        @POST("/oauth/token")
        fun getAccessToken(
                @retrofit2.http.Body oauthRequestModel: OauthRequestModel
        ): Single<OauthResponseModel>

    }

    interface Cast {

        @GET("/v1/casts")
        fun get(@QueryMap query: Map<String, String>): Single<CastResponse>

    }

    interface User {
//        fun getMe(): Single<Result<com.okysoft.annictim.API.Model.Response.User>>

        @GET("/v1/users")
        fun get(
                @Query("filter_ids") userIds: String
        ): Single<UsersResponse>

        @GET("/v1/me")
        fun getMe(
                @Query("access_token") accessToken: String
        ): Single<com.okysoft.annictim.api.model.response.User>

    }

    interface Work {
        // Apolloを使う場合はこちら
        fun latest(season: String): Single<Result<List<com.okysoft.annictim.api.model.response.Work>>>
        @GET("/v1/works")
        fun _latest(@Query("filter_season") season: String,
                    @Query("fields") fields: String?,
                    @Query("page") page: Int
                    ): Single<WorksResponse>

        @GET("/v1/works")
        fun search(@QueryMap query: Map<String, String>): Single<WorksResponse>

        interface Me {

            @GET("/v1/me/works")
            fun me(@QueryMap query: Map<String, String>): Single<WorksResponse>

        }

        @GET("/v1/works")
        fun get(@Query("filter_ids") filterIds: String): Single<WorksResponse>
    }

    interface Episode {
        @GET("/v1/episodes")
        fun get(
                @Query("filter_work_id") workId: Int,
                @Query("sort_sort_number") order: String
        ): Single<EpisodesResponse>
    }

    interface Review {
        @GET("/v1/reviews")
        fun get(
                @Query("filter_work_id") workId: Int
        ): Single<ReviewsResponse>
    }

    interface Record {
        @GET("/v1/records")
        fun get(
                @Query("filter_episode_id") episodeId: Int
        ): Single<RecordsResponse>
    }

    interface Works: Work, Work.Me

}