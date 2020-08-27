package com.okysoft.annictim.presentation.episode

import android.util.Log
import androidx.lifecycle.*
import com.okysoft.domain.model.Episode
import com.okysoft.domain.usecase.EpisodeUseCase
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EpisodesViewModel @AssistedInject constructor (
    @Assisted private val workId: Int,
    private val useCase: EpisodeUseCase
): ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(workId: Int): EpisodesViewModel
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
