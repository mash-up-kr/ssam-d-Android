package com.mashup.presentation.home.viewholder

import SimpleGapItemDecorator
import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mashup.presentation.databinding.ListItemSignalBinding
import com.mashup.presentation.home.adapter.SignalKeywordAdapter
import com.mashup.presentation.home.model.SignalUiModel
import eightbitlab.com.blurview.RenderEffectBlur


class SignalViewHolder(
    private val binding: ListItemSignalBinding,
) : RecyclerView.ViewHolder(binding.root) {
    private val keywordAdapter by lazy { SignalKeywordAdapter() }

    init {
        initView()
    }

    private fun initView() {
        with(binding) {
            val decorView = (root.context as Activity).window.decorView
            val rootView = decorView.findViewById<ViewGroup>(android.R.id.content)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                blurView.setupWith(rootView, RenderEffectBlur())
                    .setBlurRadius(BLUR_RADIUS)
            }
            blurView.clipToOutline = true

            keywordRecyclerView.adapter = keywordAdapter
            keywordRecyclerView.addItemDecoration(
                SimpleGapItemDecorator(
                    gapSize = GAP_SIZE,
                    orientation = SimpleGapDecoratorDirection.HORIZONTAL
                )
            )
        }
    }


    fun bind(value: SignalUiModel) {
        binding.signal = value
        keywordAdapter.submitList(value.getKeywordSummeryList())
    }

    companion object {
        private const val GAP_SIZE = 6
        private const val BLUR_RADIUS = 5f

        fun create(
            parent: ViewGroup
        ): SignalViewHolder {
            return SignalViewHolder(
                ListItemSignalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}