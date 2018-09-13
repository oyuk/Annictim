package com.okysoft.annictim.Presentation.ViewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.okysoft.annictim.API.Repository.EpisodeRepository
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
        private val workId: Int,
        private val episodeRepository: EpisodeRepository
): ViewModel() {

    class Factory @Inject constructor(
            private val workId: Int,
            private val repository: EpisodeRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EpisodesViewModel(workId, repository) as T
        }
    }

}
