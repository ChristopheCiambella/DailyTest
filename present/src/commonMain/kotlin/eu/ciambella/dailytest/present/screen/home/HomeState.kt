package eu.ciambella.dailytest.present.screen.home

import eu.ciambella.dailytest.domain.video.model.Video
import eu.ciambella.dailytest.domain.video.model.Videos

data class HomeState(
    val firstVideosResult: Result<Videos>? = null,
    val nextVideosResult: Result<Videos>? = null,
    val nextPage: Int? = null,
    val videos: List<Video> = emptyList(),
    val hasNextPage: Boolean? = null
)
