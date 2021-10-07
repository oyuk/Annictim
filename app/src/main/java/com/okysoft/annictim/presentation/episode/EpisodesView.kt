package com.okysoft.annictim.presentation.episode

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.okysoft.domain.model.Episode

@Composable
fun EpisodesView(viewModel: EpisodesViewModel) {
    val episodes: List<Episode> by viewModel.episodes.observeAsState(initial = emptyList())
    EpisodeList(episodes = episodes, onClick = { viewModel.tappedEpisode(it) })
}