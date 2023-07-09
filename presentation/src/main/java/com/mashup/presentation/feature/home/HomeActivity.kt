package com.mashup.presentation.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.mashup.presentation.KeyLinkApp
import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseActivity
import com.mashup.presentation.common.extension.addBadge
import com.mashup.presentation.common.extension.removeBadge
import com.mashup.presentation.common.extension.setThemeContent
import com.mashup.presentation.databinding.ActivityHomeBinding
import com.mashup.presentation.ui.theme.SsamDTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
@AndroidEntryPoint
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
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setThemeContent {
            KeyLinkApp()
        }
    }
}