package com.mashup.presentation.feature.mypage.profile.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.databinding.ItemProfileHeaderBinding
import com.mashup.presentation.feature.mypage.profile.ProfileViewType

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/10
 */
class HeaderViewHolder(private val binding: ItemProfileHeaderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindItems(item: ProfileViewType.Header) {
        with(binding) {
            header = item
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): HeaderViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return HeaderViewHolder(
                binding = ItemProfileHeaderBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
        }
    }
}