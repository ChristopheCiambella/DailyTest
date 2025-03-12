package eu.ciambella.dailytest.common.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.ciambella.dailytest.present.navigation.Action
import eu.ciambella.dailytest.present.navigation.ActionHandler
import eu.ciambella.dailytest.design.property.scaffold.ScaffoldProperty
import eu.ciambella.dailytest.domain.network.NetworkAvailableUseCase
import eu.ciambella.dailytest.domain.utils.CoroutineDispatcherProvider
import eu.ciambella.dailytest.domain.video.GetVideosUseCase
import eu.ciambella.dailytest.domain.video.model.Video
import eu.ciambella.dailytest.present.navigation.EventActionHandler
import eu.ciambella.dailytest.present.screen.home.HomeScreenMapper
import eu.ciambella.dailytest.present.screen.home.HomeState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getVideosUseCase: GetVideosUseCase,
    private val networkAvailableUseCase: NetworkAvailableUseCase,
    private val homeScreenMapper: HomeScreenMapper,
    private val actionHandler: ActionHandler,
    private val dispatcherProvider: CoroutineDispatcherProvider,
) : ViewModel(), EventActionHandler {

    private val model = MutableStateFlow(
        HomeState()
    )

    val state: StateFlow<ScaffoldProperty> = model.map {
        mapToUI(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        homeScreenMapper.mapShimmer()
    )

    private fun mapToUI(
        state: HomeState
    ): ScaffoldProperty = homeScreenMapper.map(
        eventActionHandler = this,
        homeState = state,
        onNextPageRequested = ::requestNextPage,
    )

    private var loadingJob: Job? = null

    fun create() {
        listenConnectivityChange()
        requestFirstPage()
    }

    private fun listenConnectivityChange() {
        viewModelScope.launch(dispatcherProvider.main) {
            networkAvailableUseCase.networkAvailableFlow().collect { available ->
                if (!available) {
                    return@collect
                }
                val data = model.value as? HomeState ?: return@collect
                if (data.firstVideosResult?.exceptionOrNull() != null) {
                    requestFirstPage()
                    return@collect
                }
                if (data.nextVideosResult?.exceptionOrNull() != null) {
                    requestNextPage()
                }
            }
        }
    }

    private fun requestFirstPage() {
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch(dispatcherProvider.io) {
            val result = getVideosUseCase.invoke()
            val videos = result.getOrNull()
            model.update {
                it.copy(
                    firstVideosResult = result,
                    nextVideosResult = null,
                    nextPage = videos?.nextPage,
                    videos = videos?.items ?: emptyList(),
                    hasNextPage = videos?.hasMore ?: false
                )
            }
        }
    }

    private fun requestNextPage() {
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch(dispatcherProvider.io) {
            val nextPage = model.value.nextPage ?: return@launch
            val result = getVideosUseCase.invoke(nextPage)
            val videos = result.getOrNull()
            model.update {
                it.copy(
                    nextVideosResult = result,
                    nextPage = videos?.nextPage ?: it.nextPage,
                    hasNextPage = videos?.hasMore ?: false,
                    videos = mutableListOf<Video>().apply {
                        addAll(it.videos)
                        videos?.items?.let { items ->
                            addAll(items)
                        }
                    }
                )
            }
        }
    }

    override fun handle(action: Action) {
        actionHandler.handle(action)
    }

}
