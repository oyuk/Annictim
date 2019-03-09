package com.okysoft.annictim.presentation.review

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.domain.Review
import com.okysoft.annictim.usecase.ReviewUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx2.await
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ReviewsViewModel @Inject constructor (
        private val workId: Int,
        private val useCase: ReviewUseCase,
        private val coroutineContext: CoroutineContext
): ViewModel() {

    class Factory @Inject constructor (
            private val workId: Int,
            private val useCase: ReviewUseCase,
            private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReviewsViewModel(workId, useCase, coroutineContext) as T
        }
    }

    private val job = Job()
    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>> = _reviews

    init {
        GlobalScope.launch(coroutineContext + job) {
            try {
                val response = useCase.get(workId).await()
                _reviews.postValue(response)
            } catch (throwable: Throwable) {
                Log.d("", throwable.toString())
            }
        }
    }

}
