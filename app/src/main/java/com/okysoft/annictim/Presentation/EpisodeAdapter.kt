package com.okysoft.annictim.Presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jakewharton.rxrelay2.BehaviorRelay
import com.okysoft.annictim.API.Model.Response.Episode
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ItemEpisodeBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class EpisodeAdapter: RecyclerView.Adapter<BindingViewHolder<ItemEpisodeBinding>>() {

    val items: BehaviorRelay<List<Episode>> = BehaviorRelay.createDefault(emptyList())
    private val _onClick = MutableLiveData<Episode>()
    val onClick: LiveData<Episode> = _onClick
    private val bag = CompositeDisposable()

    enum class ViewType(val num: Int)  {
        ITEM(0), FOOTER(1)
    }

    private val TAG = EpisodeAdapter::class.java.name

    init {
        items.subscribeBy(
                onNext = {notifyDataSetChanged()}
        ).addTo(bag)
    }

    @Suppress("unused")
    fun finalize() {
        bag.dispose()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemEpisodeBinding> {
        if (viewType == ViewType.ITEM.num) {
            return BindingViewHolder(parent.context, parent, R.layout.item_episode)
        }
        return BindingViewHolder(parent.context, parent, R.layout.item_loading)
    }

    override fun getItemCount(): Int {
        if (items.value.isEmpty()) { return 1 }
        return items.value.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == items.value.size) {
            return ViewType.FOOTER.num
        }
        return ViewType.ITEM.num
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ItemEpisodeBinding>, position: Int) {
        val viewType = getItemViewType(position);
        if (viewType == ViewType.FOOTER.num) {
            return
        }
        val item = items.value[position]
        holder.binding?.root?.setOnClickListener {
            _onClick.postValue(item)
        }
        (holder.binding as ItemEpisodeBinding).run {
            episode = item
        }
    }

}
