package com.mashup.presentation.ui.common.accompanist

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/23
 */
@SuppressLint("SetJavaScriptEnabled")
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
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    loadWithOverviewMode = true
                    defaultTextEncodingName = "UTF-8"
                }
            }
        }
    )

}