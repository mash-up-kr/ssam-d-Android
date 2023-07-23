package com.mashup.presentation.feature.profile.policy

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
fun PrivacyPolicyRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    PrivacyPolicyScreen(
        onBackClick = onBackClick
    )
}

@Composable
private fun PrivacyPolicyScreen(
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
            webViewUrl = "https://jslee-tech.tistory.com/64",
            onBackPressed = onBackClick
        )
    }

}