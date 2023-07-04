package com.mashup.presentation.feature.mypage.profile.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.databinding.ItemProfileAppVersionContentBinding
import com.mashup.presentation.feature.mypage.profile.ProfileViewType

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
    companion object {
        fun create(parent: ViewGroup): AppVersionViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return AppVersionViewHolder(
                binding = ItemProfileAppVersionContentBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
        }
    }
}