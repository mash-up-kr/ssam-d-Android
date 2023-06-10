package com.mashup.presentation.mypage.profile.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.databinding.ItemProfileNavigationContentBinding
import com.mashup.presentation.mypage.profile.ProfileViewType

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/10
 */
class NavigationContentViewHolder(
    private val binding: ItemProfileNavigationContentBinding,
    private val onNavigateButtonClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bindItems(item: ProfileViewType.NavigationContent) {
        with(binding) {
            content = item
            btnNavigationIcon.setOnClickListener {
                onNavigateButtonClick.invoke(item.actionId)
            }
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup, onNavigateButtonClick: (Int) -> Unit): NavigationContentViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return NavigationContentViewHolder(
                binding = ItemProfileNavigationContentBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                ),
                onNavigateButtonClick = onNavigateButtonClick
            )
        }
    }
}