package com.mashup.presentation.mypage.profile.holder

import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.databinding.ItemProfileAppVersionContentBinding
import com.mashup.presentation.mypage.profile.ProfileViewType

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/10
 */
class AppVersionViewHolder(private val binding: ItemProfileAppVersionContentBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindItems(item: ProfileViewType.AppVersionContent) {
        with(binding) {
            content = item
            executePendingBindings()
        }
    }
}