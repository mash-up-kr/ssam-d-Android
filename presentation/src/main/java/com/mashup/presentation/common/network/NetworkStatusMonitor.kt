package com.mashup.presentation.common.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/23
 */
class NetworkStatusMonitor(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkStatus = callbackFlow<NetworkStatus> {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(NetworkStatus.NetworkConnected)
            }

            override fun onLost(network: Network) {
                trySend(NetworkStatus.NetworkDisconnected)
            }

            override fun onUnavailable() {
                trySend(NetworkStatus.NetworkDisconnected)
            }
        }

        register(networkStatusCallback)

        awaitClose {
            unregister(networkStatusCallback)
        }
    }.distinctUntilChanged()

    private fun register(networkStatusCallback: ConnectivityManager.NetworkCallback) {
        connectivityManager.registerDefaultNetworkCallback(networkStatusCallback)
    }

    private fun unregister(networkStatusCallback: ConnectivityManager.NetworkCallback) {
        connectivityManager.unregisterNetworkCallback(networkStatusCallback)
    }
}

inline fun <R> Flow<NetworkStatus>.map(
    crossinline onDisconnected: suspend () -> R,
    crossinline onConnected: suspend () -> R,
): Flow<R> = map { status ->
    when (status) {
        NetworkStatus.NetworkDisconnected -> onDisconnected()
        NetworkStatus.NetworkConnected -> onConnected()
    }
}