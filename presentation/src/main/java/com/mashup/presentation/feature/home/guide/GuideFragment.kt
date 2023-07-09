package com.mashup.presentation.feature.home.guide

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigateUp
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.databinding.FragmentGuideBinding

class GuideFragment : BaseFragment<FragmentGuideBinding>(R.layout.fragment_guide) {
    override fun initViews() {
        binding.composeView.setThemeContent {
            GuideScreen(
                onClickBack = { navigateUp() }
            )
        }
    }
}