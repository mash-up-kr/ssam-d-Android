package com.mashup.presentation.mypage.profile.holder

import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.databinding.ItemProfileUserInfoBinding
import com.mashup.presentation.mypage.profile.ProfileAdapter
import com.mashup.presentation.mypage.profile.ProfileViewType

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/10
 */
class UserInfoViewHolder(
    private val binding: ItemProfileUserInfoBinding,
    onUpdateButtonClick: (ProfileAdapter.ClickEventType) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnUpdateUserName.setOnClickListener {
//            onUpdateButtonClick(ClickEventType.UpdateName)
        }
    }

    fun bindItems(item: ProfileViewType.UserInfo) {
        with(binding) {
            userInfo = item
            executePendingBindings()
        }
    }
}