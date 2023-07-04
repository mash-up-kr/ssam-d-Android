package com.mashup.presentation.feature.mypage.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/11
 */
@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    fun logout() {}

    fun updateNotificationState(checkState: Boolean) {}

}