package com.mashup.presentation.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.resizeDialogFragment

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/08
 */
open class BaseDialogFragment<VB : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    DialogFragment() {

    private var _binding: VB? = null
    val binding: VB get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.KeyLinkPopUp)
    }

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

        initViews()
    }

    /* must implement */
    open fun initViews() {}

    override fun onResume() {
        super.onResume()
        context?.resizeDialogFragment(this, 0.78f, 0.22f)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}