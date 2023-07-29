package com.mashup.presentation.feature.error

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.common.network.NetworkStatus
import com.mashup.presentation.common.network.NetworkStatusMonitor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/23
 */
class NetworkErrorActivity : ComponentActivity() {
    private val networkStatusMonitor by lazy { NetworkStatusMonitor(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        initObservers()
        super.onCreate(savedInstanceState)

        setThemeContent {
            NetworkErrorScreen(
                onBackClick = {},
                onButtonClick = {}
            )
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                networkStatusMonitor.networkStatus.collectLatest { networkStatus ->
                    when (networkStatus) {
                        is NetworkStatus.NetworkConnected -> onNetworkConnected()
                        is NetworkStatus.NetworkDisconnected -> {}
                    }
                }
            }
        }
    }

    private fun onNetworkConnected() {
        finish()
    }
}