package com.mashup.presentation.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.home.viewholder.SignalKeywordViewHolder

class SignalKeywordAdapter : RecyclerView.Adapter<SignalKeywordViewHolder>() {
    private var items: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SignalKeywordViewHolder {
        return SignalKeywordViewHolder.create(parent)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: SignalKeywordViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun submitList(keywords: List<String>) {
        items = keywords
        notifyDataSetChanged()
    }
}