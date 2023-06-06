package com.mashup.presentation.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.mashup.presentation.databinding.ViewKeyLinkButtonBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/06
 */
class KeyLinkButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewKeyLinkButtonBinding by lazy {
        ViewKeyLinkButtonBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setButtonText(text: String) {
        binding.tvButtonStatus.text = text
    }

    fun setButtonTextAppearance(@StyleRes resId: Int) {
        binding.tvButtonStatus.setTextAppearance(resId)
    }

    fun setButtonBackground(@DrawableRes resId: Int) {
        binding.clButton.setBackgroundResource(resId)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("key_link_button_text")
        fun KeyLinkButton.setKeyLinkButtonText(text: String?) {
            text?.let { setButtonText(it) }
        }

        @JvmStatic
        @BindingAdapter("key_link_button_text_appearance")
        fun KeyLinkButton.setKeyLinkButtonTextAppearance(resId: Int) {
            setButtonTextAppearance(resId)
        }
        @JvmStatic
        @BindingAdapter("key_link_button_background")
        fun KeyLinkButton.setKeyLinkButtonBackground(resId: Int) {
            setButtonBackground(resId)
        }
    }
}