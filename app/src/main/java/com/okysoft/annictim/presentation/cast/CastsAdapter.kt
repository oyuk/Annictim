package com.okysoft.annictim.presentation.cast

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxrelay2.BehaviorRelay
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ItemCastBinding
import com.okysoft.annictim.domain.Cast
import com.okysoft.annictim.presentation.BindingViewHolder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class CastsAdapter: RecyclerView.Adapter<BindingViewHolder<ItemCastBinding>>() {

    val items: BehaviorRelay<List<Cast>> = BehaviorRelay.createDefault(emptyList())
    private val _onClick = MutableLiveData<Cast>()
    val onClick: LiveData<Cast> = _onClick
    private val bag = CompositeDisposable()

    enum class ViewType(val num: Int)  {
        ITEM(0), FOOTER(1)
    }

    private val TAG = CastsAdapter::class.java.name

    init {
        items.subscribeBy(
            onNext = {notifyDataSetChanged()}
        ).addTo(bag)
    }

    @Suppress("unused")
    fun finalize() {
        bag.dispose()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemCastBinding> {
        if (viewType == ViewType.ITEM.num) {
            return BindingViewHolder(parent.context, parent, R.layout.item_cast)
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

    override fun onBindViewHolder(holder: BindingViewHolder<ItemCastBinding>, position: Int) {
        val viewType = getItemViewType(position);
        if (viewType == ViewType.FOOTER.num) {
            return
        }
        val item = items.value[position]
        holder.binding?.root?.setOnClickListener {
            _onClick.postValue(item)
        }
        (holder.binding as ItemCastBinding).apply {
            cast = item
            val characterName = item.character.name ?: ""
            characterNameTextView.visibility = if (characterName.isBlank()) View.GONE else View.VISIBLE
        }
    }

}
