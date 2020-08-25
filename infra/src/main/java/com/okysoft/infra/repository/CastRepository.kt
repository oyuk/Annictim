package com.okysoft.infra.repository

import com.okysoft.data.CastRequestParams
import com.okysoft.infra.response.CastsResponse
import com.okysoft.infra.AnnictService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CastRepository {

    fun get(requestParams: CastRequestParams): Flow<CastsResponse>

}