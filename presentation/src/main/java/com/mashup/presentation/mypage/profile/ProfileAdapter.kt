package com.mashup.presentation.mypage.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.R
import com.mashup.presentation.databinding.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/07
 */
class ProfileAdapter(private val onButtonClick: (Pair<ClickEventType, Int?>) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var optionsList: List<ProfileViewType> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_profile_user_info -> {
                val binding = ItemProfileUserInfoBinding.inflate(layoutInflater, parent, false)
                UserInfoViewHolder(binding) {
                    onButtonClick.invoke(Pair(ClickEventType.UpdateName, null))
                }
            }
            R.layout.item_profile_header -> {
                val binding = ItemProfileHeaderBinding.inflate(layoutInflater, parent, false)
                HeaderViewHolder(binding)
            }
            R.layout.item_profile_navigation_content -> {
                val binding =
                    ItemProfileNavigationContentBinding.inflate(layoutInflater, parent, false)
                NavigationContentViewHolder(binding) { actionId ->
                    onButtonClick.invoke(Pair(ClickEventType.Navigate, actionId))
                }
            }
            R.layout.item_profile_app_version_content -> {
                val binding =
                    ItemProfileAppVersionContentBinding.inflate(layoutInflater, parent, false)
                AppVersionViewHolder(binding)
            }
            R.layout.item_profile_logout_content -> {
                val binding = ItemProfileLogoutContentBinding.inflate(layoutInflater, parent, false)
                LogoutViewHolder(binding) {
                    onButtonClick.invoke(Pair(ClickEventType.Logout, null))
                }
            }
            else -> throw IllegalStateException("Unknown ViewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val option = optionsList[position]) {
            is ProfileViewType.UserInfo -> (holder as UserInfoViewHolder).bindItems(option)
            is ProfileViewType.Header -> (holder as HeaderViewHolder).bindItems(option)
            is ProfileViewType.NavigationContent ->
                (holder as NavigationContentViewHolder).bindItems(option)
            is ProfileViewType.AppVersionContent ->
                (holder as AppVersionViewHolder).bindItems(option)
            is ProfileViewType.LogoutContent -> (holder as LogoutViewHolder)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (optionsList[position]) {
            is ProfileViewType.UserInfo -> R.layout.item_profile_user_info
            is ProfileViewType.Header -> R.layout.item_profile_header
            is ProfileViewType.NavigationContent -> R.layout.item_profile_navigation_content
            is ProfileViewType.AppVersionContent -> R.layout.item_profile_app_version_content
            is ProfileViewType.LogoutContent -> R.layout.item_profile_logout_content
        }
    }

    override fun getItemCount(): Int = optionsList.size

    class UserInfoViewHolder(
        private val binding: ItemProfileUserInfoBinding,
        onUpdateButtonClick: (ClickEventType) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnUpdateUserName.setOnClickListener {
                onUpdateButtonClick(ClickEventType.UpdateName)
            }
        }

        fun bindItems(item: ProfileViewType.UserInfo) {
            with(binding) {
                userInfo = item
                executePendingBindings()
            }
        }
    }

    class HeaderViewHolder(private val binding: ItemProfileHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(item: ProfileViewType.Header) {
            with(binding) {
                header = item
                executePendingBindings()
            }
        }
    }

    class NavigationContentViewHolder(
        private val binding: ItemProfileNavigationContentBinding,
        private val onNavigateButtonClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(item: ProfileViewType.NavigationContent) {
            with(binding) {
                content = item
                btnNavigationIcon.setOnClickListener {
                    onNavigateButtonClick.invoke(content.actionId)
                }
                executePendingBindings()
            }
        }
    }

    class AppVersionViewHolder(private val binding: ItemProfileAppVersionContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(item: ProfileViewType.AppVersionContent) {
            with(binding) {
                content = item
                executePendingBindings()
            }
        }
    }

    class LogoutViewHolder(
        private val binding: ItemProfileLogoutContentBinding,
        onLogoutButtonClick: (ClickEventType) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnLogout.setOnClickListener {
                onLogoutButtonClick.invoke(ClickEventType.Logout)
            }
        }
    }

    sealed class ClickEventType {
        object UpdateName : ClickEventType()
        object Navigate : ClickEventType()
        object Logout : ClickEventType()
    }
}