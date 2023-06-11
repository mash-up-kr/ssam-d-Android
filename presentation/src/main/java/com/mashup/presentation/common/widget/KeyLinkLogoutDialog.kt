package com.mashup.presentation.common.widget

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseDialogFragment
import com.mashup.presentation.databinding.DialogLogoutBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/09
 */
class KeyLinkLogoutDialog(
    private val onLogoutClick: () -> Unit
) : BaseDialogFragment<DialogLogoutBinding>(R.layout.dialog_logout) {

    override fun initViews() {
        binding.btnDismiss.setOnClickListener {
            dismiss()
        }

        binding.btnLogout.setOnClickListener {
            onLogoutClick.invoke()
        }

    }

    companion object {
        const val DIALOG_TAG = "logout_dialog"
    }
}