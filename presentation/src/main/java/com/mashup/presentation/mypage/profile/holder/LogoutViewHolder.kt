package com.mashup.presentation.mypage.profile.holder

import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.databinding.ItemProfileLogoutContentBinding
import com.mashup.presentation.mypage.profile.ProfileAdapter

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/10
 */
class LogoutViewHolder(
    private val binding: ItemProfileLogoutContentBinding,
    onLogoutButtonClick: (ProfileAdapter.ClickEventType) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnLogout.setOnClickListener {
            onLogoutButtonClick.invoke(ProfileAdapter.ClickEventType.Logout)
        }
    }
}