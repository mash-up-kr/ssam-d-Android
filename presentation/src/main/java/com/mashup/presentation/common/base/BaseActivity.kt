package com.mashup.presentation.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
open class BaseActivity<VB : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    AppCompatActivity() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)

        initViews()
    }

    /* must implement */
    open fun initViews() {}

    override fun onDestroy() {
        super.onDestroy()
    }
}