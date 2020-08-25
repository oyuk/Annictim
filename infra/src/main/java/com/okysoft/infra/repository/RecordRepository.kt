package com.okysoft.infra.repository

import com.okysoft.infra.response.RecordsResponse
import com.okysoft.infra.AnnictService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RecordRepository {

    fun get(episodeId: Int): Flow<RecordsResponse>

}