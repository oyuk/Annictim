package com.okysoft.infra.impl

import com.apollographql.apollo.ApolloClient
import com.okysoft.infra.AnnictService
import com.okysoft.infra.repository.EpisodeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit

data class Episode(
    val id: Int,
    val numberText: String?,
    val title: String?
)

class EpisodeRepositoryImpl constructor(retrofit: Retrofit,
                                        private val apolloClient: ApolloClient): EpisodeRepository {

    private val client = retrofit.create(AnnictService.Episode::class.java)

    @ExperimentalCoroutinesApi
    override suspend fun get(workId: Int, order: String): Flow<List<Episode>> {
        return flow {
            val response = client.get(workId, order)
            emit(response.episodes.map { Episode(it.id, it.numberText, it.title) })
        }

//        return apolloClient.query(EpisodesQuery(workId)).toFlow()
//            .map {
//                val response = it.data?.searchEpisodes?.nodes?.map { node ->
//                    node?.let {
//                        Episode(node.annictId, node.numberText, node.title)
//                    }
//                }
//                return@map response?.filterNotNull() ?: emptyList()
//            }
//            .flowOn(Dispatchers.IO)
    }
}

