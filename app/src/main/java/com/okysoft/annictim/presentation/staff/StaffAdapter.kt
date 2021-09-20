package com.okysoft.annictim.presentation.staff

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxrelay2.BehaviorRelay
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ItemStaffBinding
import com.okysoft.annictim.presentation.BindingViewHolder
import com.okysoft.annictim.presentation.program.ProgramsAdapter
import com.okysoft.domain.model.Staff
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class StaffAdapter: RecyclerView.Adapter<BindingViewHolder<ItemStaffBinding>>() {

    val items: BehaviorRelay<List<Staff>> = BehaviorRelay.createDefault(emptyList())
    private val _onClick = MutableLiveData<Staff>()
    val onClick: LiveData<Staff> = _onClick
    private val bag = CompositeDisposable()

    enum class ViewType(val num: Int)  {
        ITEM(0), FOOTER(1)
    }

    private val TAG = StaffAdapter::class.java.name

    init {
        items.subscribeBy(
            onNext = {notifyDataSetChanged()}
        ).addTo(bag)
    }

    @Suppress("unused")
    fun finalize() {
        bag.dispose()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemStaffBinding> {
        if (viewType == ViewType.ITEM.num) {
            return BindingViewHolder(parent.context, parent, R.layout.item_staff)
        }
        return BindingViewHolder(parent.context, parent, R.layout.item_loading)
    }

    override fun getItemCount(): Int {
        return items.value?.let {
            if (it.isEmpty()) { return@let 1 }
            return@let it.size
        } ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return items.value?.let {
            if (position == it.size) {
                return@let ProgramsAdapter.ViewType.FOOTER.num
            }
            return@let ProgramsAdapter.ViewType.ITEM.num
        } ?: ProgramsAdapter.ViewType.ITEM.num
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ItemStaffBinding>, position: Int) {
        val viewType = getItemViewType(position);
        if (viewType == ViewType.FOOTER.num) {
            return
        }
        val item = items.value?.let { it[position] } ?: return
        holder.binding?.root?.setOnClickListener {
            _onClick.postValue(item)
        }
        (holder.binding as ItemStaffBinding).apply {
            staff = item
            roleTextView.visibility = if (item.roleText.isBlank()) View.GONE else View.VISIBLE
        }
    }

}

