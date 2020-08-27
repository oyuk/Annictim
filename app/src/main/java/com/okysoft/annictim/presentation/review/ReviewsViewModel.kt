package com.okysoft.annictim.presentation.review

import android.util.Log
import androidx.lifecycle.*
import com.okysoft.domain.model.Review
import com.okysoft.domain.usecase.ReviewUseCase
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ReviewsViewModel @AssistedInject constructor (
    @Assisted private val workId: Int,
    private val useCase: ReviewUseCase
): ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(workId: Int): ReviewsViewModel
    }

    companion object {
        fun provideFactory(
            factory: Factory,
            workId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return factory.create(workId) as T
            }
        }
    }

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
