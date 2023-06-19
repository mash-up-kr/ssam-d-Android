package com.mashup.presentation.home

import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseActivity
import com.mashup.presentation.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(binding.fcvHome.id) as NavHostFragment
    }
    private val navController by lazy { navHostFragment.navController }

    override fun initViews() {
        initBottomNavigationView()
        initDestinationChangeListener()
    }

    private fun initBottomNavigationView() {
        binding.bnvHome.setupWithNavController(navController)
        addSignalIconItemView()
    }

    private fun initDestinationChangeListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bnvHome.visibility = when (destination.id) {
                /* 디자인 명세에 따라 수정 */
                R.id.homeFragment, R.id.reelsFragment -> View.VISIBLE
                else -> View.GONE
            }
        }
    }

    private fun addSignalIconItemView() {
        val navigationMenuView = binding.bnvHome.getChildAt(0) as BottomNavigationMenuView
        val navigationItemView = navigationMenuView.getChildAt(1) as BottomNavigationItemView
        val signalItemView = LayoutInflater.from(this)
            .inflate(R.layout.menu_bottom_navigation_signal, binding.bnvHome, false)
        navigationItemView.addView(signalItemView)
    }
}