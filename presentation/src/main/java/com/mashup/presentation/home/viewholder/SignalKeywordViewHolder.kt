package com.mashup.presentation.home.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.databinding.ListItemSignalKeywordBinding


class SignalKeywordViewHolder(
    private val binding: ListItemSignalKeywordBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(keyword: String) {
        binding.keyword = keyword
    }

    companion object {
        fun create(
            parent: ViewGroup
        ): SignalKeywordViewHolder {
            return SignalKeywordViewHolder(
                ListItemSignalKeywordBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}