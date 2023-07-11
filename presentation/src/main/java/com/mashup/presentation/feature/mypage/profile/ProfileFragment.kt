package com.mashup.presentation.feature.mypage.profile

import androidx.fragment.app.viewModels
import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigate
import com.mashup.presentation.common.widget.KeyLinkLogoutDialog
import com.mashup.presentation.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val profileAdapter: ProfileAdapter by lazy { ProfileAdapter() }
    private val viewModel: ProfileViewModel by viewModels()
    override fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding.rvProfile) {
            adapter = profileAdapter.apply {
                optionsList = Options(requireActivity()).getOptions()
                setClickListener(object : ProfileAdapter.ClickEventListener {
                    override fun onNotificationClick(isChecked: Boolean) {
                        viewModel.updateNotificationState(isChecked)
                    }

                    override fun onNavigateClick(actionId: Int) {
                        navigate(actionId)
                    }

                    override fun onLogoutClick() {
                        KeyLinkLogoutDialog {
                            viewModel.logout()
                        }.show(childFragmentManager, KeyLinkLogoutDialog.DIALOG_TAG)
                    }
                })
            }
            addItemDecoration(ProfileItemDecoration())
        }
    }
}