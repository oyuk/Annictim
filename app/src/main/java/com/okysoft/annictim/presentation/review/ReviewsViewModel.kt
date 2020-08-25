package com.okysoft.annictim.presentation.review

import android.util.Log
import androidx.lifecycle.*
import com.okysoft.domain.model.Review
import com.okysoft.domain.usecase.ReviewUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ReviewsViewModel @Inject constructor (
    private val workId: Int,
    private val useCase: ReviewUseCase
): ViewModel() {

    class Factory @Inject constructor (
        private val workId: Int,
        private val useCase: ReviewUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReviewsViewModel(workId, useCase) as T
        }
    }

    private val job = Job()
    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>> = _reviews

    init {
        viewModelScope.launch {
            try {
                useCase.get(workId)
                    .collect {
                        _reviews.postValue(it)
                    }
            } catch (throwable: Throwable) {
                Log.d("", throwable.toString())
            }
        }
    }

}
