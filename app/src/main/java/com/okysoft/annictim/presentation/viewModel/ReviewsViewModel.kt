package com.okysoft.annictim.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.api.model.response.Review
import com.okysoft.annictim.api.repository.ReviewRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ReviewsViewModel @Inject constructor (
        private val workId: Int,
        private val reviewRepository: ReviewRepository,
        private val coroutineContext: CoroutineContext
): ViewModel() {

    class Factory @Inject constructor (
            private val workId: Int,
            private val repository: ReviewRepository,
            private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReviewsViewModel(workId, repository,coroutineContext) as T
        }
    }

    private val job = Job()
    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>> = _reviews

    fun fetch() {
        GlobalScope.launch(coroutineContext + job) {
            try {
                val response = reviewRepository.get(workId).await()
                _reviews.postValue(response.reviews)
            } catch (throwable: Throwable) {
                Log.d("", throwable.toString())
            }
        }
    }

}
