package com.mashup.presentation.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mashup.presentation.KeyLinkApp
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.common.network.NetworkStatus
import com.mashup.presentation.common.network.NetworkStatusMonitor
import com.mashup.presentation.feature.error.NetworkErrorActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val networkStatusMonitor by lazy { NetworkStatusMonitor(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()

        setThemeContent {
            KeyLinkApp()
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                networkStatusMonitor.networkStatus.collectLatest { networkStatus ->
                    when (networkStatus) {
                        is NetworkStatus.NetworkConnected -> {}
                        is NetworkStatus.NetworkDisconnected -> onNetworkDisconnected()
                    }
                }
            }
        }
    }

    private fun onNetworkDisconnected() {
        startActivity(Intent(this, NetworkErrorActivity::class.java))
    }
}
