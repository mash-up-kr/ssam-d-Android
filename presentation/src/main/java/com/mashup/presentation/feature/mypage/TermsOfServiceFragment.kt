package com.mashup.presentation.feature.mypage

import android.annotation.SuppressLint
import android.webkit.WebViewClient
import android.widget.Toast
import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.navigateUp
import com.mashup.presentation.databinding.FragmentTermsOfServiceBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
class TermsOfServiceFragment :
    BaseFragment<FragmentTermsOfServiceBinding>(R.layout.fragment_terms_of_service) {
    @SuppressLint("SetJavaScriptEnabled")
    override fun initViews() {
        with(binding) {
            wvTos.apply {
                settings.apply {
                    javaScriptEnabled = true
                    loadWithOverviewMode = true
                    useWideViewPort = true
                }
                webViewClient = WebViewClient()
                loadUrl("https://www.naver.com")
            }

            tbTos.setOnBackButtonClickListener {
                navigateUp()
            }
        }
    }
}