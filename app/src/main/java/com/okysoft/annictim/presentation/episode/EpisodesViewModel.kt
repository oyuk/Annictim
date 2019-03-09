package com.okysoft.annictim.presentation.episode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.domain.Episode
import com.okysoft.annictim.usecase.EpisodeUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.rx2.await
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class EpisodesViewModel @Inject constructor (
        private val workId: Int,
        private val useCase: EpisodeUseCase,
        private val coroutineContext: CoroutineContext
): ViewModel() {

    class Factory @Inject constructor (
            private val workId: Int,
            private val useCase: EpisodeUseCase,
            private val coroutineContext: CoroutineContext
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EpisodesViewModel(workId, useCase, coroutineContext) as T
        }
    }

    private val job = Job()
    private val _episodes = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>> = _episodes

    fun fetch() {
        GlobalScope.launch(coroutineContext + job) {
            try  {
                val response = useCase.get(workId).await()
                _episodes.postValue(response)
            } catch (throwable: Throwable) {
                Log.d("", throwable.toString())

            }
        }
    }

}
