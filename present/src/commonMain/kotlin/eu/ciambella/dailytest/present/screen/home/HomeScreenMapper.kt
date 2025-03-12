package eu.ciambella.dailytest.present.screen.home

import eu.ciambella.dailytest.design.property.Property
import eu.ciambella.dailytest.design.property.components.DividerProperty
import eu.ciambella.dailytest.design.property.components.LoadingProperty
import eu.ciambella.dailytest.design.property.components.VideoShimmerProperty
import eu.ciambella.dailytest.design.property.content.ErrorContentProperty
import eu.ciambella.dailytest.design.property.content.LazyColumnContentProperty
import eu.ciambella.dailytest.design.property.scaffold.ScaffoldProperty
import eu.ciambella.dailytest.design.property.topbar.TopAppBarProperty
import eu.ciambella.dailytest.domain.video.model.Video
import eu.ciambella.dailytest.domain.video.model.Videos
import eu.ciambella.dailytest.present.common.mapper.ErrorPropertyMapper
import eu.ciambella.dailytest.present.common.mapper.ScaffoldPropertyMapper
import eu.ciambella.dailytest.present.common.mapper.TopAppBarPropertyMapper
import eu.ciambella.dailytest.present.common.mapper.VideoPropertyMapper
import eu.ciambella.dailytest.present.navigation.Action
import eu.ciambella.dailytest.present.navigation.EventActionHandler
import eu.ciambella.dailytest.present.navigation.NavigationElement
import eu.ciambella.dailytest.present.resource.StringResources

class HomeScreenMapper(
    private val scaffoldPropertyMapper: ScaffoldPropertyMapper,
    private val topAppBarPropertyMapper: TopAppBarPropertyMapper,
    private val stringResources: StringResources,
    private val errorPropertyMapper: ErrorPropertyMapper,
    private val videoPropertyMapper: VideoPropertyMapper,
) {

    private fun topAppBarProperty(): TopAppBarProperty = topAppBarPropertyMapper.default(
        title = stringResources.homeTitle
    )

    fun mapShimmer() = scaffoldPropertyMapper.map(
        topAppBarProperty = topAppBarProperty(),
        contentProperty = LazyColumnContentProperty(
            scrollEnabled = false,
            items = listOf(
                VideoShimmerProperty,
                DividerProperty,
                VideoShimmerProperty,
                DividerProperty,
                VideoShimmerProperty,
                DividerProperty,
                VideoShimmerProperty,
                DividerProperty,
                VideoShimmerProperty,
                DividerProperty,
                VideoShimmerProperty,
            )
        )
    )

    fun map(
        eventActionHandler: EventActionHandler,
        homeState: HomeState,
        onNextPageRequested: () -> Unit,
    ): ScaffoldProperty {
        if (homeState.firstVideosResult == null) {
            return mapShimmer()
        }
        return homeState.firstVideosResult.fold(
            onSuccess = {
                scaffoldPropertyMapper.map(
                    topAppBarProperty = topAppBarProperty(),
                    contentProperty = LazyColumnContentProperty(
                        items = buildVideoScreen(
                            homeState = homeState,
                            eventActionHandler = eventActionHandler,
                            onNextPageRequested = onNextPageRequested,
                        ),
                    )
                )
            },
            onFailure = {
                scaffoldPropertyMapper.map(
                    topAppBarProperty = topAppBarProperty(),
                    contentProperty = ErrorContentProperty(
                        property = errorPropertyMapper.genericError {
                            onNextPageRequested.invoke()
                        }
                    ),
                )
            }
        )
    }

    private fun buildVideoScreen(
        homeState: HomeState,
        eventActionHandler: EventActionHandler,
        onNextPageRequested: () -> Unit,
    ): List<Property> = mutableListOf<Property>().also { properties ->
        properties.addAll(
            mapVideos(
                homeState.videos,
                eventActionHandler
            )
        )
        properties.addAll(
            mapError(
                nextAudiosResult = homeState.nextVideosResult,
                requestNextPage = onNextPageRequested
            )
        )
        properties.addAll(
            mapLoading(
                hasNext = homeState.hasNextPage ?: false,
                onNextPageRequested = onNextPageRequested
            )
        )
    }

    private fun mapVideos(
        videos: List<Video>,
        eventActionHandler: EventActionHandler,
    ) = mutableListOf<Property>().apply {
        videos.forEachIndexed { index, video ->
            if (index != 0) {
                add(DividerProperty)
            }
            add(
                videoPropertyMapper.mapToVideoProperty(video) {
                    eventActionHandler.handle(
                        Action.Navigation(
                            NavigationElement.Player(
                                videoId = video.id
                            )
                        )
                    )
                }
            )
        }
    }

    private fun mapError(
        nextAudiosResult: Result<Videos>?,
        requestNextPage: () -> Unit,
    ): List<Property> = nextAudiosResult?.exceptionOrNull()?.let {
        listOf(
            DividerProperty,
            errorPropertyMapper.genericError(
                onClick = requestNextPage
            )
        )
    } ?: emptyList()

    private fun mapLoading(
        hasNext: Boolean,
        onNextPageRequested: () -> Unit,
    ): List<Property> = if (hasNext) {
        listOf(LoadingProperty(onNextPageRequested))
    } else {
        listOf()
    }

}
