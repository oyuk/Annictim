package com.okysoft.infra

import com.okysoft.data.OauthRequestModel
import com.okysoft.infra.response.*
import kotlinx.coroutines.flow.Flow
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
        suspend fun get(@QueryMap query: Map<String, String>): ProgramsResponse
    }


    interface Me {

        @POST("v1/me/statuses")
        suspend fun status(@QueryMap query: Map<String, String>): Response<Unit>

    }

    interface Staff {

        @GET("/v1/staffs")
        suspend fun get(@QueryMap query: Map<String, String>): StaffsResponse

    }

    interface Cast {

        @GET("/v1/casts")
        suspend fun get(@QueryMap query: Map<String, String>): CastsResponse

    }

    interface User {

        @GET("/v1/users")
        suspend fun get(
                @Query("filter_ids") userIds: String
        ): UsersResponse

        @GET("/v1/me")
        suspend fun getMe(
                @Query("access_token") accessToken: String
        ): UserResponse

    }

    interface Work {

        interface Me {

            @GET("/v1/me/works")
            suspend fun me(@QueryMap query: Map<String, String>): WorksResponse

        }

        @GET("/v1/works")
        suspend fun get(@QueryMap query: Map<String, String>): WorksResponse
    }

    interface Episode {
        @GET("/v1/episodes")
        suspend fun get(
                @Query("filter_work_id") workId: Int,
                @Query("sort_sort_number") order: String
        ): EpisodesResponse
    }

    interface Review {
        @GET("/v1/reviews")
        suspend fun get(
                @Query("filter_work_id") workId: Int
        ): ReviewsResponse
    }

    interface Record {
        @GET("/v1/records")
        suspend fun get(
                @Query("filter_episode_id") episodeId: Int
        ): RecordsResponse
    }

    interface Works: Work, Work.Me

}