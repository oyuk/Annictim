package com.okysoft.annictim.presentation.episode

import android.util.Log
import androidx.lifecycle.*
import com.okysoft.domain.model.Episode
import com.okysoft.domain.usecase.EpisodeUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class EpisodesViewModel @Inject constructor (
    private val workId: Int,
    private val useCase: EpisodeUseCase
): ViewModel() {

    class Factory @Inject constructor (
        private val workId: Int,
        private val useCase: EpisodeUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EpisodesViewModel(workId, useCase) as T
        }
    }

    private val job = Job()
    private val _episodes = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>> = _episodes

    fun fetch() {
        viewModelScope.launch {
            try  {
                val response = useCase.get(workId)
                _episodes.postValue(response)
            } catch (throwable: Throwable) {
                Log.d("", throwable.toString())

            }
        }
    }

}
