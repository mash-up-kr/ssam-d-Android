package com.mashup.presentation.mypage.profile.holder

import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.databinding.ItemProfileNotificationBinding
import com.mashup.presentation.mypage.profile.ProfileViewType

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/10
 */
class NotificationViewHolder(
    private val binding: ItemProfileNotificationBinding,
    isSwitchChecked: (Boolean) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.swNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.swNotification.isChecked = true
            }
            isSwitchChecked.invoke(isChecked)
        }
    }

    fun bindItems(item: ProfileViewType.NotificationContent) {
        with(binding) {
            content = item
            executePendingBindings()
        }
    }
}