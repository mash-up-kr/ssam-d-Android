package com.mashup.presentation.feature.mypage.profile.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.databinding.ItemProfileUserInfoBinding
import com.mashup.presentation.feature.mypage.profile.ProfileViewType

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/10
 */
class UserInfoViewHolder(
    private val binding: ItemProfileUserInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItems(item: ProfileViewType.UserInfo) {
        with(binding) {
            userInfo = item
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): UserInfoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return UserInfoViewHolder(
                binding = ItemProfileUserInfoBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
        }
    }
}