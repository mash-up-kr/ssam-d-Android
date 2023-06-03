package com.mashup.presentation.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
open class BaseFragment<VB : ViewDataBinding>(@LayoutRes private val layoutId: Int) : Fragment() {

    private var _binding: VB? = null
    val binding: VB get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        initViews()
    }

    /* must implement */
    open fun initViews() {}

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}