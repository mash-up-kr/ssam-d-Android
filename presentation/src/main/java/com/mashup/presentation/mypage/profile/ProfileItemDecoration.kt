package com.mashup.presentation.mypage.profile

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.mashup.presentation.common.extension.dpToPx

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/08
 */
class ProfileItemDecoration : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
            .let { adapterPosition ->
                if (adapterPosition == RecyclerView.NO_POSITION) return else adapterPosition
            }

        outRect.top = when (position) {
            1 -> 32.dpToPx()
            3 -> 20.dpToPx()
            7 -> 53.dpToPx()
            else -> 0.dpToPx()
        }
    }
}