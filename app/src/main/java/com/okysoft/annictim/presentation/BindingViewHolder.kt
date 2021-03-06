package com.okysoft.annictim.presentation

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class BindingViewHolder<out T: ViewDataBinding>(context: Context?, parent: ViewGroup?, resource: Int)
    : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(resource, parent, false))  {
    val binding = DataBindingUtil.bind<T>(itemView)
}