package com.mashup.presentation.profile

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigate
import com.mashup.presentation.databinding.FragmentProfileBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    override fun initViews() {
        initClickListeners()
    }

    private fun initClickListeners() {
        with(binding) {
            btnUpdateUserName.setOnClickListener {
                /* 다이얼로그 */
            }
            btnNotificationDetail.setOnClickListener {
                navigate(R.id.action_profile_to_notificationSetting)
            }
            btnTosDetail.setOnClickListener {
                navigate(R.id.action_profile_to_termsOfService)
            }
            btnPrivacyPolicyDetail.setOnClickListener {
                navigate(R.id.action_profile_to_privacyPolicy)
            }
            btnOpenSourceDetail.setOnClickListener {
                navigate(R.id.action_profile_to_openSource)
            }
            btnLogout.setOnClickListener {
                /* 로그아웃 로직 */
            }
        }
    }
}