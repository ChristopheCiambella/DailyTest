package eu.ciambella.dailytest.data.repository.video.mapper

import eu.ciambella.dailytest.data.api.entity.VideoEntity
import eu.ciambella.dailytest.data.api.response.VideosResponse
import eu.ciambella.dailytest.domain.video.model.Video
import eu.ciambella.dailytest.domain.video.model.Videos
import org.jetbrains.annotations.VisibleForTesting

class VideosResponseMapper {

    fun mapToVideos(
        response: VideosResponse,
        requestedPage: Int,
    ) = Videos(
        items = response.list?.mapNotNull(::mapVideoEntity) ?: emptyList(),
        nextPage = (response.page ?: requestedPage) + 1,
        hasMore = response.hasMore ?: false
    )

    @VisibleForTesting
    fun mapVideoEntity(
        entity: VideoEntity,
    ): Video? {
        if (entity.id == null || entity.title == null) {
            return null
        }
        return Video(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            thumbnailUrl = entity.thumbnailUrl,
            createdTimeMs = entity.createdTime?.let {
                it * 1000L
            }
        )
    }

}
