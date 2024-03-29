package com.okysoft.annictim.presentation.record

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxrelay2.BehaviorRelay
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ItemRecordBinding
import com.okysoft.annictim.extension.toDate
import com.okysoft.annictim.extension.toReadableDateString
import com.okysoft.annictim.presentation.BindingViewHolder
import com.okysoft.annictim.presentation.program.ProgramsAdapter
import com.okysoft.domain.model.Record
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class RecordsAdapter: RecyclerView.Adapter<BindingViewHolder<ItemRecordBinding>>() {

    val items: BehaviorRelay<List<Record>> = BehaviorRelay.createDefault(emptyList())
    private val _onClick = MutableLiveData<Record>()
    val onClick: LiveData<Record> = _onClick
    private val _onClickUser = MutableLiveData<Pair<Int, View>>()
    val onClickUser: LiveData<Pair<Int, View>> = _onClickUser
    private val bag = CompositeDisposable()

    enum class ViewType(val num: Int)  {
        ITEM(0), FOOTER(1)
    }

    private val TAG = RecordsAdapter::class.java.name

    init {
        items.subscribeBy(
                onNext = {notifyDataSetChanged()}
        ).addTo(bag)
    }

    @Suppress("unused")
    fun finalize() {
        bag.dispose()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemRecordBinding> {
        if (viewType == ViewType.ITEM.num) {
            return BindingViewHolder(parent.context, parent, R.layout.item_record)
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

    override fun onBindViewHolder(holder: BindingViewHolder<ItemRecordBinding>, position: Int) {
        val viewType = getItemViewType(position);
        if (viewType == ViewType.FOOTER.num) {
            return
        }
        val item = items.value?.let { it[position] } ?: return
        holder.binding?.root?.setOnClickListener {
            _onClick.postValue(item)
        }
        holder.binding?.userImage?.setOnClickListener {
            _onClickUser.postValue(Pair(item.user.id, it))
        }
        (holder.binding as ItemRecordBinding).run {
            record = item
            textViewDate.text = item.createdAt.toDate()?.toReadableDateString()
            commentTextView.visibility = if (item.comment.isEmpty()) View.GONE else View.VISIBLE
        }
    }

}
