package com.okysoft.annictim.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import com.okysoft.annictim.DiffUtilCallback
import com.okysoft.annictim.R
import com.okysoft.annictim.api.model.response.Work
import com.okysoft.annictim.databinding.ItemWorkBinding
import com.okysoft.annictim.extension.setImage
import kotlin.properties.Delegates

data class WorkClickItem(
    val work: Work,
    val imateView: ImageView
)

class WorksAdapter: RecyclerView.Adapter<BindingViewHolder<ItemWorkBinding>>() {

    private var items: List<Work> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(DiffUtilCallback(old, new), false).dispatchUpdatesTo(this)
    }
    private val _onClick = MutableLiveData<WorkClickItem>()
    val onClick: LiveData<WorkClickItem> = _onClick

    fun updateItem(i: List<Work>) {
        items = i
    }

    enum class ViewType(val num: Int)  {
        ITEM(0), FOOTER(1)
    }

    private val TAG = WorksAdapter::class.java.name

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemWorkBinding> {
        if (viewType == ViewType.ITEM.num) {
            return BindingViewHolder(parent.context, parent, R.layout.item_work)
        }
        return BindingViewHolder(parent.context, parent, R.layout.item_loading)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
//        if (position == items.size) {
//            return ViewType.FOOTER.num
//        }
        return ViewType.ITEM.num
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ItemWorkBinding>, position: Int) {
        val viewType = getItemViewType(position);
        if (viewType == ViewType.FOOTER.num) {
            return
        }
        val item = items[position]
        holder.binding?.root?.setOnClickListener {
            _onClick.postValue(WorkClickItem(item, holder.binding.imageView))
        }
        (holder.binding as ItemWorkBinding).apply {
            work = item
            imageView.setImage(item.images.recommendedUrl)
        }
    }

}
