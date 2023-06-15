package com.mashup.presentation.mypage

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import android.widget.Toast
import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigateUp
import com.mashup.presentation.databinding.FragmentPrivacyPolicyBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
class PrivacyPolicyFragment :
    BaseFragment<FragmentPrivacyPolicyBinding>(R.layout.fragment_privacy_policy) {
    @SuppressLint("SetJavaScriptEnabled")
    override fun initViews() {
        with(binding) {
            wvPrivacyPolicy.apply {
                settings.apply {
                    javaScriptEnabled = true
                    loadWithOverviewMode = true
                    useWideViewPort = true
                }
                webViewClient = WebViewClient()
                loadUrl("https://www.naver.com")
            }

            tbPrivacyPolicy.setOnBackButtonClickListener {
                navigateUp()
            }
        }
    }
}