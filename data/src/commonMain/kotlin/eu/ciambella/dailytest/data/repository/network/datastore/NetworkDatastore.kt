package eu.ciambella.dailytest.data.repository.network.datastore

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("MissingPermission")
class NetworkDatastore(
    application: Application,
) {

    val networkAvailableStateFlow = MutableStateFlow(false)

    private val connectivityManager: ConnectivityManager by lazy {
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private var availableCount = 0

    private val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            availableCount++
            networkAvailableStateFlow.tryEmit(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            availableCount--
            networkAvailableStateFlow.tryEmit(availableCount > 0)
        }
    }

    fun register() {
        try {
            try {
                val available = isNetworkAvailable()
                networkAvailableStateFlow.tryEmit(available)
            } catch (t: Throwable) {
                println("ERROR: Failed to retrieve network availability : ${t.message}")
            }
            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
                .build()
            availableCount = 0
            connectivityManager.registerNetworkCallback(request, callback)
        } catch (t: Throwable) {
            println("ERROR: Failed to register network callback : ${t.message}")
        }
    }

    fun unregister() {
        try {
            connectivityManager.unregisterNetworkCallback(callback)
        } catch (t: Throwable) {
            println("ERROR: Failed to unregister network callback : ${t.message}")
        }
    }

    fun isNetworkAvailable(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
