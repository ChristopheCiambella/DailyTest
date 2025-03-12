package eu.ciambella.dailytest.common.screen.player

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.ciambella.dailytest.design.property.scaffold.ScaffoldProperty
import eu.ciambella.dailytest.domain.utils.CoroutineDispatcherProvider
import eu.ciambella.dailytest.domain.video.GetVideoUrlUseCase
import eu.ciambella.dailytest.present.screen.player.PlayerScreenMapper
import eu.ciambella.dailytest.present.screen.player.PlayerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerViewModel(
    savedStateHandle: SavedStateHandle,
    private val getVideoUrlUseCase: GetVideoUrlUseCase,
    private val playerScreenMapper: PlayerScreenMapper,
    private val dispatcherProvider: CoroutineDispatcherProvider,
) : ViewModel() {

    private val arguments = PlayerArgs(savedStateHandle)

    private val model = MutableStateFlow(PlayerState())

    val state: StateFlow<ScaffoldProperty> = model.map {
        mapToUI(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        playerScreenMapper.mapShimmer()
    )

    private fun mapToUI(
        state: PlayerState
    ): ScaffoldProperty = playerScreenMapper.map(
        playerState = state,
    )

    fun create() {
        viewModelScope.launch(dispatcherProvider.io) {
            val result = getVideoUrlUseCase.invoke(arguments.videoId)
            model.update {
                it.copy(
                    videoUrl = result
                )
            }
        }
    }

}
