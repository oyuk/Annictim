package com.okysoft.annictim.api

import com.okysoft.annictim.api.model.request.OauthRequestModel
import com.okysoft.annictim.api.model.response.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface AnnictService {

    interface Oauth {

        @POST("/oauth/token")
        fun getAccessToken(
                @retrofit2.http.Body oauthRequestModel: OauthRequestModel
        ): Deferred<OauthResponseModel>

    }

    interface Program {

        @GET("/v1/me/programs")
        fun get(@QueryMap query: Map<String, String>): Deferred<ProgramsResponse>
    }


    interface Me {

        @POST("v1/me/statuses")
        fun status(@QueryMap query: Map<String, String>): Deferred<Unit>

    }

    interface Staff {

        @GET("/v1/staffs")
        fun get(@QueryMap query: Map<String, String>): Deferred<StaffResponse>

    }

    interface Cast {

        @GET("/v1/casts")
        fun get(@QueryMap query: Map<String, String>): Deferred<CastResponse>

    }

    interface User {
//        fun getMe(): Single<Result<com.okysoft.annictim.API.Model.Response.User>>

        @GET("/v1/users")
        fun get(
                @Query("filter_ids") userIds: String
        ): Deferred<UsersResponse>

        @GET("/v1/me")
        fun getMe(
                @Query("access_token") accessToken: String
        ): Deferred<com.okysoft.annictim.api.model.response.User>

    }

    interface Work {
        // Apolloを使う場合はこちら
//        fun latest(season: String): Single<Result<List<com.okysoft.annictim.api.model.response.Work>>>

        interface Me {

            @GET("/v1/me/works")
            fun me(@QueryMap query: Map<String, String>): Deferred<WorksResponse>

        }

        @GET("/v1/works")
        fun get(@QueryMap query: Map<String, String>): Deferred<WorksResponse>
    }

    interface Episode {
        @GET("/v1/episodes")
        fun get(
                @Query("filter_work_id") workId: Int,
                @Query("sort_sort_number") order: String
        ): Deferred<EpisodesResponse>
    }

    interface Review {
        @GET("/v1/reviews")
        fun get(
                @Query("filter_work_id") workId: Int
        ): Deferred<ReviewsResponse>
    }

    interface Record {
        @GET("/v1/records")
        fun get(
                @Query("filter_episode_id") episodeId: Int
        ): Deferred<RecordsResponse>
    }

    interface Works: Work, Work.Me

}