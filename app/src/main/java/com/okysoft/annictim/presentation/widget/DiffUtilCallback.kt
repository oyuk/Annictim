package com.okysoft.annictim.presentation.widget

import androidx.recyclerview.widget.DiffUtil
import com.okysoft.common.Diffable

class DiffUtilCallback(val old: List<Diffable>, val new: List<com.okysoft.common.Diffable>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
        = old[oldItemPosition].isTheSame(new[newItemPosition])

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
        = old[oldItemPosition].isContentsTheSame(new[newItemPosition])

}

