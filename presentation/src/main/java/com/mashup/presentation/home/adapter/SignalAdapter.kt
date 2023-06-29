package com.mashup.presentation.home.adapter

import android.view.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mashup.presentation.home.model.SignalUiModel
import com.mashup.presentation.home.viewholder.SignalViewHolder

class SignalAdapter : ListAdapter<SignalUiModel, SignalViewHolder>(
    SignalDiffUtilCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignalViewHolder {
        return SignalViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SignalViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class SignalDiffUtilCallback : DiffUtil.ItemCallback<SignalUiModel>() {
    override fun areItemsTheSame(oldItem: SignalUiModel, newItem: SignalUiModel): Boolean {
        return oldItem.nickname == newItem.nickname
    }

    override fun areContentsTheSame(oldItem: SignalUiModel, newItem: SignalUiModel): Boolean {
        return oldItem == newItem
    }
}