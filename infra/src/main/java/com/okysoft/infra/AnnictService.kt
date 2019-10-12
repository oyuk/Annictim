package com.okysoft.infra

import com.okysoft.data.OauthRequestModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface AnnictService {

    interface Oauth {

        @POST("/oauth/token")
        fun getAccessToken(
                @retrofit2.http.Body oauthRequestModel: com.okysoft.data.OauthRequestModel
        ): Deferred<com.okysoft.data.OauthResponseModel>

    }

    interface Program {

        @GET("/v1/me/programs")
        fun get(@QueryMap query: Map<String, String>): Deferred<com.okysoft.data.ProgramsResponse>
    }


    interface Me {

        @POST("v1/me/statuses")
        fun status(@QueryMap query: Map<String, String>): Deferred<Response<Unit>>

    }

    interface Staff {

        @GET("/v1/staffs")
        fun get(@QueryMap query: Map<String, String>): Deferred<com.okysoft.data.StaffsResponse>

    }

    interface Cast {

        @GET("/v1/casts")
        fun get(@QueryMap query: Map<String, String>): Deferred<com.okysoft.data.CastResponse>

    }

    interface User {

        @GET("/v1/users")
        fun get(
                @Query("filter_ids") userIds: String
        ): Deferred<com.okysoft.data.UsersResponse>

        @GET("/v1/me")
        fun getMe(
                @Query("access_token") accessToken: String
        ): Deferred<com.okysoft.data.UserResponse>

    }

    interface Work {

        interface Me {

            @GET("/v1/me/works")
            fun me(@QueryMap query: Map<String, String>): Deferred<com.okysoft.data.WorksResponse>

        }

        @GET("/v1/works")
        fun get(@QueryMap query: Map<String, String>): Deferred<com.okysoft.data.WorksResponse>
    }

    interface Episode {
        @GET("/v1/episodes")
        fun get(
                @Query("filter_work_id") workId: Int,
                @Query("sort_sort_number") order: String
        ): Deferred<com.okysoft.data.EpisodesResponse>
    }

    interface Review {
        @GET("/v1/reviews")
        fun get(
                @Query("filter_work_id") workId: Int
        ): Deferred<com.okysoft.data.ReviewsResponse>
    }

    interface Record {
        @GET("/v1/records")
        fun get(
                @Query("filter_episode_id") episodeId: Int
        ): Deferred<com.okysoft.data.RecordsResponse>
    }

    interface Works: Work, Work.Me

}