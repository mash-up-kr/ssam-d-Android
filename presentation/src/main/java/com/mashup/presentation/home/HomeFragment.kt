package com.mashup.presentation.home

import SimpleGapItemDecorator
import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.databinding.FragmentHomeBinding
import com.mashup.presentation.home.adapter.SignalAdapter
import com.mashup.presentation.home.model.SignalUiModel

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/04
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val adapter: SignalAdapter by lazy {
        SignalAdapter()
    }

    override fun initViews() {
        super.initViews()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            SimpleGapItemDecorator(
                gapSize = 12,
                orientation = SimpleGapDecoratorDirection.VERTICAL
            )
        )
    }
        }
    }
}