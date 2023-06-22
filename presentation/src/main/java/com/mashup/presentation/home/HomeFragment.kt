package com.mashup.presentation.home

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.databinding.FragmentHomeBinding
import com.mashup.presentation.ui.setThemeContent

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/04
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun initViews() {
        binding.composeView.setThemeContent {
            HomeScreen()
        }
    }
}