package com.okysoft.domain.usecase

import com.okysoft.domain.model.WorkDetail
import kotlinx.coroutines.flow.Flow

interface WorkDetailUseCase {
    fun get(id: Int): Flow<WorkDetail>
}