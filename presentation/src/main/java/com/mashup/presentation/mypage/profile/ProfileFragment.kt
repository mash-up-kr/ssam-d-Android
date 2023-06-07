package com.mashup.presentation.mypage.profile

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigate
import com.mashup.presentation.databinding.FragmentProfileBinding
import com.mashup.presentation.mypage.profile.ProfileAdapter.ClickEventType
import timber.log.Timber

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val profileAdapter: ProfileAdapter by lazy {
        ProfileAdapter { eventTypePair ->
            when (eventTypePair.first) {
                is ClickEventType.UpdateName -> updateUserName()
                is ClickEventType.Logout -> logout()
                is ClickEventType.Navigate -> navigate(eventTypePair.second ?: -1)
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
        Timber.e("이름 업데이트")
    }

    private fun logout() {
        Timber.e("로그아웃 로직")
    }
}