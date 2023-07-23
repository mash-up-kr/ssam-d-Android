package com.mashup.presentation.feature.profile.tos

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.common.accompanist.KeyLinkWebView
import com.mashup.presentation.ui.theme.Black

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/23
 */
@Composable
fun TermsOfServiceRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    TermsOfServiceScreen(
        onBackClick = onBackClick
    )
}

@Composable
private fun TermsOfServiceScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        backgroundColor = Black,
        topBar = {
            KeyLinkToolbar(onClickBack = onBackClick)
        }
    ) { paddingValues ->
        KeyLinkWebView(
            modifier = Modifier.padding(paddingValues),
            webViewUrl = "https://jslee-tech.tistory.com/63",
            onBackPressed = onBackClick
        )
    }
}