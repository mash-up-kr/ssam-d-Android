package com.mashup.presentation.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.mashup.presentation.databinding.ViewKeyLinkToolbarBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/05
 */
class KeyLinkToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewKeyLinkToolbarBinding by lazy {
        ViewKeyLinkToolbarBinding.inflate(LayoutInflater.from(context), this, true)
    }


    fun setOnBackButtonClickListener(onClick: (KeyLinkToolbar) -> Unit) {
        binding.btnBack.setOnClickListener {
            onClick(this)
        }
    }

    fun setRightButtonDrawable(@DrawableRes drawableId: Int) =
        binding.btnRight.setImageResource(drawableId)

    fun setOnRightButtonClickListener(onClick: (KeyLinkToolbar) -> Unit) {
        binding.btnRight.setOnClickListener {
            onClick(this)
        }
    }

    fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun setTitleGravity(gravity: Int = Gravity.START) {
        binding.tvTitle.gravity = gravity
    }

    companion object {

        @JvmStatic
        @BindingAdapter("key_link_title_gravity")
        fun KeyLinkToolbar.setKeyLinkTitleGravity(gravity: Int) = setTitleGravity(gravity)

        @JvmStatic
        @BindingAdapter("key_link_title")
        fun KeyLinkToolbar.setKeyLinkTitle(title: String?) = title?.let {
            setTitle(it)
        }

        @JvmStatic
        @BindingAdapter("key_link_right_drawable")
        fun KeyLinkToolbar.setKeyLinkRightDrawable(@DrawableRes drawableId: Int) {
            setRightButtonDrawable(drawableId)
        }
    }
}