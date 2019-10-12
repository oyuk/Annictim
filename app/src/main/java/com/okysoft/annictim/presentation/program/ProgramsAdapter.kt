package com.okysoft.annictim.presentation.program

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxrelay2.BehaviorRelay
import com.okysoft.annictim.R
import com.okysoft.annictim.databinding.ItemProgramBinding
import com.okysoft.annictim.extension.setImage
import com.okysoft.annictim.extension.toDate
import com.okysoft.annictim.extension.toReadableDateTimeString
import com.okysoft.annictim.presentation.BindingViewHolder
import com.okysoft.domain.model.Program
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ProgramsAdapter: RecyclerView.Adapter<BindingViewHolder<ItemProgramBinding>>() {

    val items: BehaviorRelay<List<Program>> = BehaviorRelay.createDefault(emptyList())
    private val _onClick = MutableLiveData<Program>()
    val onClick: LiveData<Program> = _onClick
    private val bag = CompositeDisposable()

    enum class ViewType(val num: Int)  {
        ITEM(0), FOOTER(1)
    }

    private val TAG = ProgramsAdapter::class.java.name

    init {
        items.subscribeBy(
            onNext = {notifyDataSetChanged()}
        ).addTo(bag)
    }

    @Suppress("unused")
    fun finalize() {
        bag.dispose()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ItemProgramBinding> {
        if (viewType == ViewType.ITEM.num) {
            return BindingViewHolder(parent.context, parent, R.layout.item_program)
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

    override fun onBindViewHolder(holder: BindingViewHolder<ItemProgramBinding>, position: Int) {
        val viewType = getItemViewType(position);
        if (viewType == ViewType.FOOTER.num) {
            return
        }
        val item = items.value[position]
        holder.binding?.root?.setOnClickListener {
            _onClick.postValue(item)
        }
        (holder.binding as ItemProgramBinding).apply {
            program = item
            imageView.setImage(item.workResponse.images?.recommendedUrl ?: "")
            startedAt.text = item.startedAt.toDate()?.toReadableDateTimeString()
        }
    }

}
