package com.mashup.presentation.mypage.profile

import android.widget.Toast
import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigate
import com.mashup.presentation.databinding.FragmentProfileBinding
import com.mashup.presentation.mypage.profile.ProfileAdapter.ClickEventType

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val profileAdapter: ProfileAdapter by lazy {
        ProfileAdapter { eventType ->
            when (eventType.first) {
                is ClickEventType.UpdateName -> {}
                is ClickEventType.Notification ->
                    updateNotificationStatus(eventType.second ?: false)
                is ClickEventType.Navigate -> navigate(eventType.third ?: -1)
                is ClickEventType.Logout -> logout()
            }
        }
    }

    override fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding.rvProfile) {
            adapter = profileAdapter.apply {
                optionsList = Options(requireActivity()).getOptions()
            }
            addItemDecoration(ProfileItemDecoration())
        }
    }

    private fun updateUserName() {
        /* 이름 업데이트 */
    }

    private fun updateNotificationStatus(isChecked: Boolean) {
        Toast.makeText(requireContext(), "상태 : $isChecked", Toast.LENGTH_SHORT).show()
    }

    private fun logout() {
        /* 로그아웃 로직 */
    }
}