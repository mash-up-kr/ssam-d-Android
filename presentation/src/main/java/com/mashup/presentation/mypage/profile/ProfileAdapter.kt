package com.mashup.presentation.mypage.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.R
import com.mashup.presentation.databinding.*
import com.mashup.presentation.mypage.profile.holder.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/07
 */
class ProfileAdapter(private val onButtonClick: (Triple<ClickEventType, Boolean?, Int?>) -> Unit) :
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
//                    onButtonClick.invoke(Pair(ClickEventType.UpdateName, null))
                }
            }
            R.layout.item_profile_notification -> {
                val binding =
                    ItemProfileNotificationBinding.inflate(layoutInflater, parent, false)
                NotificationViewHolder(binding) {
                    onButtonClick.invoke(Triple(ClickEventType.Notification, it, null))
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
                    onButtonClick.invoke(Triple(ClickEventType.Navigate, null, actionId))
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
                    onButtonClick.invoke(Triple(ClickEventType.Logout, null, null))
                }
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

    sealed class ClickEventType {
        object UpdateName : ClickEventType()
        object Notification : ClickEventType()
        object Navigate : ClickEventType()
        object Logout : ClickEventType()
    }
}