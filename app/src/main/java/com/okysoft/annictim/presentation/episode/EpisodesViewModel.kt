package com.okysoft.annictim.presentation.episode

import android.util.Log
import androidx.lifecycle.*
import com.okysoft.domain.model.Episode
import com.okysoft.domain.usecase.EpisodeUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class EpisodesViewModel constructor (
    private val workId: Int,
    private val useCase: EpisodeUseCase
): ViewModel() {

    class Factory @Inject constructor (
        @Named("EpisodesFragment_WorkId") private val workId: Int,
        private val useCase: EpisodeUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EpisodesViewModel(workId, useCase) as T
        }
    }

    private val _episodes = MutableLiveData<List<Episode>>()
    val episodes: LiveData<List<Episode>> = _episodes

    fun fetch() {
        viewModelScope.launch {
            try  {
                useCase.get(workId).collect {
                    _episodes.postValue(it)
                }
            } catch (throwable: Throwable) {
                Log.d("", throwable.toString())

            }
        }
    }

}
