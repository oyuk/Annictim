package com.okysoft.annictim.Presentation.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.okysoft.annictim.API.Model.Response.Review
import com.okysoft.annictim.API.Repository.ReviewRepository
import com.okysoft.annictim.Result
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ReviewsViewModel @Inject constructor (
        private val workId: Int,
        private val reviewRepository: ReviewRepository
): ViewModel() {

    class Factory @Inject constructor (
            private val workId: Int,
            private val repository: ReviewRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ReviewsViewModel(workId, repository) as T
        }
    }

    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>> = _reviews
    private val compositeDisposable = CompositeDisposable()

    fun fetch() {
        reviewRepository.get(workId)
                .subscribe(
                        {
                            when (it) {
                                is Result.Success -> {
                                    _reviews.postValue((_reviews.value ?: listOf()).plus(it.data))
                                }
                                is Result.Failure -> {
                                    Log.d("", it.e.toString())
                                }
                            }
                        },
                        { throwable ->
                            Log.d("", throwable.toString())
                        }
                )
                .addTo(compositeDisposable)
    }

}