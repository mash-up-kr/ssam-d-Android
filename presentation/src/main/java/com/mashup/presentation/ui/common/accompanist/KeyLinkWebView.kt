package com.mashup.presentation.ui.common.accompanist

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/23
 */
@Composable
fun KeyLinkWebView(
    webViewUrl: String,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val webViewState = rememberWebViewState(url = webViewUrl)
    val webViewNavigator = rememberWebViewNavigator()

    BackHandler(true) {
        onBackPressed()
    }

    WebView(
        modifier = modifier,
        state = webViewState,
        navigator = webViewNavigator,
        onCreated = { webView ->
            with(webView) {
                settings.run {
                    domStorageEnabled = true
                    loadWithOverviewMode = true
                    defaultTextEncodingName = "UTF-8"
                }
            }
        }
    )

}