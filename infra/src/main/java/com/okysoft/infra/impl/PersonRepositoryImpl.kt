package com.okysoft.infra.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toFlow
import com.okysoft.infra.AnnictService
import com.okysoft.infra.PersonQuery
import com.okysoft.infra.WorkQuery
import com.okysoft.infra.fragment.Person
import com.okysoft.infra.repository.PersonRepository
import com.okysoft.infra.repository.WorkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(private val client: ApolloClient): PersonRepository {

    override fun get(id: Int): Flow<Person?> {
        return client.query(PersonQuery(id)).toFlow()
            .map {
                val person = it.data?.searchPeople.nodes?.let { l ->
                    l.mapNotNull { node -> node?.fragments?.person }
                }?.firstOrNull()
                if (person != null) {
                    return@map person
                }
                throw NullPointerException()
            }
    }

}