package eu.ciambella.dailytest.domain.network

import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    val networkAvailableFlow: Flow<Boolean>
    fun unregister()
    fun register()
    fun isNetworkAvailable(): Boolean
}
