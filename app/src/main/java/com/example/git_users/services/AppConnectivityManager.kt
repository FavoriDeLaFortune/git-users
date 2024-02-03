package com.example.git_users.services

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class AppConnectivityManager @Inject constructor(
    context: Context,
    private val coroutineScope: CoroutineScope
) {
    private val connectivityManager = getSystemService(context, ConnectivityManager::class.java)

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .build()
    private val isConnected: Boolean
        get() {
            val activeNetwork = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager?.activeNetwork
            } else {
                return connectivityManager?.activeNetworkInfo?.isConnected == true
            }
            return if (activeNetwork == null) {
                false
            } else {
                val netCapabilities = connectivityManager?.getNetworkCapabilities(activeNetwork)
                (netCapabilities != null
                        && netCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        && netCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED))
            }
        }

    val connectionStateFlow: StateFlow<Boolean>
        get() = _connectionFlow
            .stateIn(
                scope = coroutineScope,
                started = SharingStarted.WhileSubscribed(1000),
                initialValue = isConnected
            )

    private val _connectionFlow = callbackFlow {
        val networkCallback = object : NetworkCallback() {
            override fun onLost(network: Network) {
                trySend(false)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                ) {
                    trySend(true)
                }
            }
        }
        subscribe(networkCallback)
        awaitClose {
            unsubscribe(networkCallback)
        }
    }

    private fun subscribe(networkCallback: NetworkCallback) {
        connectivityManager?.registerNetworkCallback(networkRequest, networkCallback)
    }

    private fun unsubscribe(networkCallback: NetworkCallback) {
        connectivityManager?.unregisterNetworkCallback(networkCallback)
    }
}