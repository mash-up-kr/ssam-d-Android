package com.mashup.presentation.common.widget

import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseDialogFragment
import com.mashup.presentation.databinding.DialogEditNameBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/06
 */
class KeyLinkUserNameDialog : BaseDialogFragment<DialogEditNameBinding>(R.layout.dialog_edit_name) {

    companion object {
        const val DIALOG_TAG = "user_name_dialog"
    }
}