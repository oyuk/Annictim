package com.okysoft.annictim.presentation.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.okysoft.annictim.databinding.ItemEpisodeBinding
import com.okysoft.domain.model.Episode

class EpisodeViewHolder(val binding: ItemEpisodeBinding):
    RecyclerView.ViewHolder(binding.root) {
    fun bind(episode: Episode) {
        binding.episode = episode
        binding.executePendingBindings()
    }
}

class EpisodeDiffCallback: DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}

class EpisodesAdapter: ListAdapter<Episode, EpisodeViewHolder>(EpisodeDiffCallback()) {

    private val _onClick = MutableLiveData<Episode>()
    val onClick: LiveData<Episode> = _onClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EpisodeViewHolder(ItemEpisodeBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.root.setOnClickListener {
            _onClick.postValue(item)
        }
    }

}
