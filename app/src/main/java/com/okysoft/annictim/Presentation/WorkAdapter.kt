package com.okysoft.annictim.Presentation

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.jakewharton.rxrelay2.BehaviorRelay
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ItemWorkBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class WorkAdapter: RecyclerView.Adapter<BindingViewHolder<ItemWorkBinding>>() {

    val items: BehaviorRelay<List<Work>> = BehaviorRelay.createDefault(emptyList())
    private val bag = CompositeDisposable()

    enum class ViewType(val num: Int)  {
        ITEM(0), FOOTER(1)
    }

    private val TAG = WorkAdapter::class.java.name

    init {
        items.subscribeBy {
            notifyDataSetChanged()
        }.addTo(bag)
    }

    @Suppress("unused")
    fun finalize() {
        bag.dispose()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemWorkBinding> {
        if (viewType == ViewType.ITEM.num) {
            return BindingViewHolder(parent.context, parent, R.layout.item_work)
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

    override fun onBindViewHolder(holder: BindingViewHolder<ItemWorkBinding>, position: Int) {
        val viewType = getItemViewType(position);
        if (viewType == ViewType.FOOTER.num) {
            return
        }
        val item = items.value[position]
        (holder.binding as ItemWorkBinding).run {
            work = item
        }
    }

}
