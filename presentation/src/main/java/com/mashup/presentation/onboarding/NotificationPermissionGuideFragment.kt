package com.mashup.presentation.onboarding

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.mashup.presentation.R
import com.mashup.presentation.common.base.BaseFragment
import com.mashup.presentation.common.extension.makeSnackBar
import com.mashup.presentation.databinding.FragmentNotificationPermissionGuideBinding
import com.mashup.presentation.ui.common.KeyLinkMintText
import com.mashup.presentation.common.extension.setThemeContent
import dagger.hilt.android.AndroidEntryPoint

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

    private val requestNotificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(requireActivity(), "권한 허용", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireActivity(), "권한 거부", Toast.LENGTH_SHORT).show()
            }
        }

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

    private fun requestNotificationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                Toast.makeText(requireActivity(), "이미 권한 존재", Toast.LENGTH_SHORT).show()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                binding.root.makeSnackBar("설정으로 이동합니다", "Settings") {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        data = Uri.fromParts("package", "com.mashup.ssamd", null)
                    }
                    startActivity(intent)
                }
            }
            else -> {
                requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}