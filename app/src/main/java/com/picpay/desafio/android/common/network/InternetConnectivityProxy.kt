package com.picpay.desafio.android.common.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.M)
class InternetConnectivityApi23OrGreater @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : InternetConnectivity {
    override fun hasConnection(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}

@Suppress("DEPRECATION")
class InternetConnectivityApi22OrLess @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : InternetConnectivity {
    override fun hasConnection(): Boolean {
        val info = connectivityManager.activeNetworkInfo ?: return false
        return info.isConnected
    }
}

class InternetConnectivityProxy @Inject constructor(
    private val api23OrGreater: InternetConnectivityApi23OrGreater,
    private val api22OrLess: InternetConnectivityApi22OrLess,
) : InternetConnectivity {

    override fun hasConnection(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            api23OrGreater.hasConnection()
        } else {
            api22OrLess.hasConnection()
        }
    }

}
