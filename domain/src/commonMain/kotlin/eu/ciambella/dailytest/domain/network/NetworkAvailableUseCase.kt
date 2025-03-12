package eu.ciambella.dailytest.domain.network

class NetworkAvailableUseCase(
    private val networkRepository: NetworkRepository
) {
    fun networkAvailableFlow() = networkRepository.networkAvailableFlow

    fun onApplicationEnterInBackground() {
        networkRepository.unregister()
    }

    fun onApplicationEnterInForeground() {
        networkRepository.register()
    }
}
