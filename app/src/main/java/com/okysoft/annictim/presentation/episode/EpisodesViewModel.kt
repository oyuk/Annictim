package com.okysoft.annictim.presentation.episode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.infra.api.model.response.EpisodeResponse
import com.okysoft.annictim.infra.api.repository.EpisodeRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class EpisodesViewModel @Inject constructor (
        private val workId: Int,
        private val episodeRepository: EpisodeRepository,
        private val coroutineContext: CoroutineContext
): ViewModel() {

    class Factory @Inject constructor (
            private val workId: Int,
            private val repository: EpisodeRepository,
            private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EpisodesViewModel(workId, repository, coroutineContext) as T
        }
    }

    private val job = Job()
    private val _episodes = MutableLiveData<List<EpisodeResponse>>()
    val episodes: LiveData<List<EpisodeResponse>> = _episodes

    fun fetch() {
        GlobalScope.launch(coroutineContext + job) {
            try  {
                val response = episodeRepository.get(workId).await()
                _episodes.postValue(response.episodes)
            } catch (throwable: Throwable) {
                Log.d("", throwable.toString())

            }
        }
    }

}
