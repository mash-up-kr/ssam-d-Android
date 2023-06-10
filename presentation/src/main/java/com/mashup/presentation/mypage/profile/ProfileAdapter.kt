package com.mashup.presentation.mypage.profile

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.R
import com.mashup.presentation.mypage.profile.holder.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/07
 */
class ProfileAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var clickListener: ClickEventListener

    var optionsList: List<ProfileViewType> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setClickListener(clickEventListener: ClickEventListener) {
        this.clickListener = clickEventListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_profile_user_info -> UserInfoViewHolder.create(parent)
            R.layout.item_profile_notification -> {
                NotificationViewHolder.create(parent) { isChecked ->
                    clickListener.onNotificationClick(isChecked)
                }
            }
            R.layout.item_profile_header -> HeaderViewHolder.create(parent)
            R.layout.item_profile_navigation_content -> {
                NavigationContentViewHolder.create(parent) { actionId ->
                    clickListener.onNavigateClick(actionId)
                }
            }
            R.layout.item_profile_app_version_content -> AppVersionViewHolder.create(parent)
            R.layout.item_profile_logout_content -> {
                LogoutViewHolder.create(parent) { clickListener.onLogoutClick() }
            }
            else -> throw IllegalStateException("Unknown ViewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val option = optionsList[position]) {
            is ProfileViewType.UserInfo -> (holder as UserInfoViewHolder).bindItems(option)
            is ProfileViewType.Header -> (holder as HeaderViewHolder).bindItems(option)
            is ProfileViewType.NotificationContent ->
                (holder as NotificationViewHolder).bindItems(option)
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
            is ProfileViewType.NotificationContent -> R.layout.item_profile_notification
            is ProfileViewType.NavigationContent -> R.layout.item_profile_navigation_content
            is ProfileViewType.AppVersionContent -> R.layout.item_profile_app_version_content
            is ProfileViewType.LogoutContent -> R.layout.item_profile_logout_content
        }
    }

    override fun getItemCount(): Int = optionsList.size

    interface ClickEventListener {
        fun onNotificationClick(isChecked: Boolean)
        fun onNavigateClick(actionId: Int)
        fun onLogoutClick()
    }
}