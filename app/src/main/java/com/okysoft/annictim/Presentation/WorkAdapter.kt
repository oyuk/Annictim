package com.okysoft.annictim.Presentation

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ItemWorkBinding

class WorkAdapter: RecyclerView.Adapter<BindingViewHolder<ItemWorkBinding>>() {

    enum class ViewType(val num: Int)  {
        ITEM(0), FOOTER(1)
    }

    private val TAG = WorkAdapter::class.java.name

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemWorkBinding> {
        if (viewType == ViewType.ITEM.num) {
            return BindingViewHolder(parent.context, parent, R.layout.item_work)
        }
        return BindingViewHolder(parent.context, parent, R.layout.item_loading)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ItemWorkBinding>, position: Int) {
        val viewType = getItemViewType(position);
        if (viewType == ViewType.FOOTER.num) {
            return
        }
    }

}
