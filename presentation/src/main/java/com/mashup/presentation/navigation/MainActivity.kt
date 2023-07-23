package com.mashup.presentation.navigation

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
    private val networkStatusMonitor: NetworkStatusMonitor by lazy {
        NetworkStatusMonitor(
            context = this,
            coroutineScope = lifecycleScope
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()

        setThemeContent {
            KeyLinkApp()
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                networkStatusMonitor.networkStatus.collectLatest { networkStatus ->
                    when (networkStatus) {
                        is NetworkStatus.NetworkConnected -> Log.e("NETWORK", "연결")
                        is NetworkStatus.NetworkDisconnected -> Log.e("NETWORK", "연결끊김")
                    }
                }
            }
        }
    }
}


//class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
//    private val navHostFragment by lazy {
//        supportFragmentManager.findFragmentById(binding.fcvHome.id) as NavHostFragment
//    }
//    private val navController by lazy { navHostFragment.navController }
//
//    override fun initViews() {
//        initBottomNavigationView()
//        initDestinationChangeListener()
//    }
//
//    private fun initBottomNavigationView() {
//        binding.bnvHome.setupWithNavController(navController)
//        addSignalIconItemView()
//        binding.bnvHome.addBadge(this, 1000)
//    }
//
//    private fun initDestinationChangeListener() {
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            binding.bnvHome.visibility = when (destination.id) {
//                /* 디자인 명세에 따라 수정 */
//                R.id.homeFragment -> View.VISIBLE
//                R.id.chatFragment -> {
//                    binding.bnvHome.removeBadge()
//                    View.VISIBLE
//                }
//                else -> View.GONE
//            }
//        }
//    }
//
//    private fun addSignalIconItemView() {
//        val navigationMenuView = binding.bnvHome.getChildAt(0) as BottomNavigationMenuView
//        val navigationItemView = navigationMenuView.getChildAt(1) as BottomNavigationItemView
//        val signalItemView = LayoutInflater.from(this)
//            .inflate(R.layout.menu_bottom_navigation_signal, binding.bnvHome, false)
//        navigationItemView.addView(signalItemView)
//    }
//}
