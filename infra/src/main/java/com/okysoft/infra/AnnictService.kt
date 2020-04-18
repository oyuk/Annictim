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
        suspend fun getAccessToken(
                @retrofit2.http.Body oauthRequestModel: OauthRequestModel
        ): com.okysoft.data.OauthResponseModel

    }

    interface Program {

        @GET("/v1/me/programs")
        suspend fun get(@QueryMap query: Map<String, String>): com.okysoft.data.ProgramsResponse
    }


    interface Me {

        @POST("v1/me/statuses")
        suspend fun status(@QueryMap query: Map<String, String>): Response<Unit>

    }

    interface Staff {

        @GET("/v1/staffs")
        suspend fun get(@QueryMap query: Map<String, String>): com.okysoft.data.StaffsResponse

    }

    interface Cast {

        @GET("/v1/casts")
        suspend fun get(@QueryMap query: Map<String, String>): com.okysoft.data.CastResponse

    }

    interface User {

        @GET("/v1/users")
        suspend fun get(
                @Query("filter_ids") userIds: String
        ): com.okysoft.data.UsersResponse

        @GET("/v1/me")
        suspend fun getMe(
                @Query("access_token") accessToken: String
        ): com.okysoft.data.UserResponse

    }

    interface Work {

        interface Me {

            @GET("/v1/me/works")
            suspend fun me(@QueryMap query: Map<String, String>): com.okysoft.data.WorksResponse

        }

        @GET("/v1/works")
        suspend fun get(@QueryMap query: Map<String, String>): com.okysoft.data.WorksResponse
    }

    interface Episode {
        @GET("/v1/episodes")
        suspend fun get(
                @Query("filter_work_id") workId: Int,
                @Query("sort_sort_number") order: String
        ): com.okysoft.data.EpisodesResponse
    }

    interface Review {
        @GET("/v1/reviews")
        suspend fun get(
                @Query("filter_work_id") workId: Int
        ): com.okysoft.data.ReviewsResponse
    }

    interface Record {
        @GET("/v1/records")
        suspend fun get(
                @Query("filter_episode_id") episodeId: Int
        ): com.okysoft.data.RecordsResponse
    }

    interface Works: Work, Work.Me

}