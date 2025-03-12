package eu.ciambella.dailytest.data.repository.network

import eu.ciambella.dailytest.data.repository.network.datastore.NetworkDatastore
import eu.ciambella.dailytest.domain.network.NetworkRepository
import kotlinx.coroutines.flow.Flow

class NetworkRepositoryImpl(
    private val networkDatastore: NetworkDatastore
) : NetworkRepository {

    override val networkAvailableFlow: Flow<Boolean>
        get() = networkDatastore.networkAvailableStateFlow

    override fun unregister() {
        networkDatastore.unregister()
    }

    override fun register() {
        networkDatastore.register()
    }

    override fun isNetworkAvailable(): Boolean = networkDatastore.isNetworkAvailable()
}
