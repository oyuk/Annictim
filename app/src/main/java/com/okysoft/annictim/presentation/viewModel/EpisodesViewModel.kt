package com.okysoft.annictim.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.util.Log
import com.okysoft.annictim.api.model.response.Episode
import com.okysoft.annictim.api.repository.EpisodeRepository
import com.okysoft.annictim.Result
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class EpisodesViewModel @Inject constructor (
        private val workId: Int,
        private val episodeRepository: EpisodeRepository
): ViewModel() {

    class Factory @Inject constructor (
            private val workId: Int,
            private val repository: EpisodeRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EpisodesViewModel(workId, repository) as T
        }
    }

    private val _episodes = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>> = _episodes
    private val compositeDisposable = CompositeDisposable()

    fun fetch() {
        episodeRepository.get(workId)
                .subscribe(
                        {
                            when (it) {
                                is Result.Success -> {
                                    val a = (_episodes.value ?: listOf()).plus(it.data)
                                    _episodes.postValue(a)
                                }
                                is Result.Failure -> {
                                    Log.d("", it.throwable.toString())
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
