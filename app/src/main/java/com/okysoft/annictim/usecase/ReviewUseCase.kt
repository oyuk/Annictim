package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Review
import io.reactivex.Single

interface UseCase<T, R> {

    fun get(requestParams: T): Single<List<R>>

}

interface ReviewUseCase {

    fun get(workId: Int): Single<List<Review>>

}
