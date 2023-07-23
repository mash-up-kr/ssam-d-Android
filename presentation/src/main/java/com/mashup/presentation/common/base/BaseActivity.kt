package com.mashup.presentation.common.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mashup.presentation.common.network.NetworkStatus
import com.mashup.presentation.common.network.NetworkStatusMonitor
import com.mashup.presentation.feature.error.NetworkErrorActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
open class BaseActivity<VB : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    AppCompatActivity() {

    private val networkStatusMonitor by lazy { NetworkStatusMonitor(this) }
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        binding = DataBindingUtil.setContentView(this, layoutId)

        initViews()
    }

    /* must implement */
    open fun initViews() {}

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