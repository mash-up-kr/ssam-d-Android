package com.mashup.presentation.mypage.profile

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
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
        ProfileAdapter { event ->
            when (event) {
                ClickEventType.UpdateName -> updateUserName()
                ClickEventType.Logout -> logout()
                else -> {
                    Timber.e("미처리 이벤트")
                }
            }
        }
    }

    override fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvProfile.adapter = profileAdapter.apply {
            optionsList = Options(requireActivity()).getOptions()
        }
    }

    private fun updateUserName() {
        Timber.e("이름 업데이트")
    }

    private fun logout() {
        Timber.e("로그아웃 로직")
    }
}