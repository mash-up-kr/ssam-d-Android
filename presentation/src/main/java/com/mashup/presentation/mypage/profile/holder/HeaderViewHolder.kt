package com.mashup.presentation.mypage.profile.holder

import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.databinding.ItemProfileHeaderBinding
import com.mashup.presentation.mypage.profile.ProfileViewType

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
}