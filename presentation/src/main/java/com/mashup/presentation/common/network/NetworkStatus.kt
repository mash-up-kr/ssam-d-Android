package com.mashup.presentation.common.network

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/23
 */
sealed class NetworkStatus() {
    object NetworkConnected : NetworkStatus()
    object NetworkDisconnected : NetworkStatus()
}
