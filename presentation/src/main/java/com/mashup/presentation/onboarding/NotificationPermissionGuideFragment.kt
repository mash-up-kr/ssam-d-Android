package com.mashup.presentation.onboarding

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.databinding.FragmentNotificationPermissionGuideBinding
import com.mashup.presentation.ui.common.KeyLinkMintText
import com.mashup.presentation.ui.setThemeContent
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/03
 */
@AndroidEntryPoint
class NotificationPermissionGuideFragment :
    BaseFragment<FragmentNotificationPermissionGuideBinding>(
        R.layout.fragment_notification_permission_guide
    ) {
    override fun initViews() {
        binding.cpvTitle.setThemeContent {
            KeyLinkMintText(
                text = "내 행성으로 온 시그널을\n놓치지 않고 받아볼까요?",
                modifier = Modifier
            )
        }

        binding.btnAllow.setOnClickListener {
            requestNotificationPermission()
        }
    }
    private val requestNotificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(requireActivity(), "권한 허용", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireActivity(), "권한 거부", Toast.LENGTH_SHORT).show()
            }
        }

    private fun requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                // 다른 런타임 퍼미션이랑 비슷한 과정
                if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                    // 왜 알림을 허용해야하는지 유저에게 알려주기를 권장
                    Toast.makeText(requireContext(), "클릭 1", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "클릭 2", Toast.LENGTH_SHORT).show()
                    requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            } else {
                Toast.makeText(requireContext(), "Android 12 이하", Toast.LENGTH_SHORT).show()
                // 안드로이드 12 이하는 알림에 런타임 퍼미션 없으니, 설정가서 켜보라고 권해볼 수 있겠다.
            }
        } else {
            Timber.e("ASASDASDADAS")
        }
    }
}