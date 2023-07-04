package com.mashup.presentation.feature.mypage.profile.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.databinding.ItemProfileLogoutContentBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/10
 */
class LogoutViewHolder(
    private val binding: ItemProfileLogoutContentBinding,
    onLogoutButtonClick: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnLogout.setOnClickListener {
            onLogoutButtonClick.invoke()
        }
    }

    companion object {
        fun create(parent: ViewGroup, onLogoutButtonClick: () -> Unit): LogoutViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return LogoutViewHolder(
                binding = ItemProfileLogoutContentBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                ),
                onLogoutButtonClick = onLogoutButtonClick
            )
        }
    }
}