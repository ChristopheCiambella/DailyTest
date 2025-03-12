package eu.ciambella.dailytest.present.screen.player

import eu.ciambella.dailytest.design.property.components.VideoPlayerProperty
import eu.ciambella.dailytest.design.property.content.ErrorContentProperty
import eu.ciambella.dailytest.design.property.content.LazyColumnContentProperty
import eu.ciambella.dailytest.design.property.content.VideoPlayerContentProperty
import eu.ciambella.dailytest.design.property.scaffold.ScaffoldProperty
import eu.ciambella.dailytest.present.common.mapper.ErrorPropertyMapper
import eu.ciambella.dailytest.present.common.mapper.ScaffoldPropertyMapper
import eu.ciambella.dailytest.present.common.mapper.TopAppBarPropertyMapper
import eu.ciambella.dailytest.present.resource.StringResources

class PlayerScreenMapper(
    private val stringResources: StringResources,
    private val scaffoldPropertyMapper: ScaffoldPropertyMapper,
    private val topAppBarPropertyMapper: TopAppBarPropertyMapper,
    private val errorPropertyMapper: ErrorPropertyMapper,
) {

    fun mapShimmer() = scaffoldPropertyMapper.map(
        contentProperty = LazyColumnContentProperty(
            scrollEnabled = false,
            items = listOf()
        )
    )

    fun map(
        playerState: PlayerState,
    ): ScaffoldProperty {
        if (playerState.videoUrl == null) {
            return mapShimmer()
        }
        return playerState.videoUrl.fold(
            onSuccess = ::mapSuccess,
            onFailure = {
                mapFailure()
            }
        )
    }

    private fun mapSuccess(
        videoUrl: String,
    ) = scaffoldPropertyMapper.map(
        contentProperty = VideoPlayerContentProperty(
            videoPlayerProperty = VideoPlayerProperty(
                videoUrl = videoUrl
            ),
        )
    )

    private fun mapFailure() = scaffoldPropertyMapper.map(
        topAppBarProperty = topAppBarPropertyMapper.default(
            title = stringResources.homeTitle
        ),
        contentProperty = ErrorContentProperty(
            property = errorPropertyMapper.genericError()
        ),
    )

}
