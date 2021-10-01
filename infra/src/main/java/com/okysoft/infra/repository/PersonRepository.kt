package com.okysoft.infra.repository

import com.okysoft.infra.fragment.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun get(id: Int): Flow<Person?>
}