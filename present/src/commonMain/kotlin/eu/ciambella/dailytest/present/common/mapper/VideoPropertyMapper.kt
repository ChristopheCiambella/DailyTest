package eu.ciambella.dailytest.present.common.mapper

import eu.ciambella.dailytest.present.resource.DrawableResources
import eu.ciambella.dailytest.design.property.components.AdaptiveImageProperty
import eu.ciambella.dailytest.design.property.components.VideoProperty
import eu.ciambella.dailytest.domain.video.model.Video
import eu.ciambella.dailytest.present.common.formatter.DateFormatter
import java.util.Calendar

class VideoPropertyMapper(
    private val drawableResources: DrawableResources,
    private val dateFormatter: DateFormatter
) {

    fun mapToVideoProperty(
        video: Video,
        onVideoClick: () -> Unit
    ) = VideoProperty(
        title = video.title,
        description = if (video.description.isNullOrEmpty()) {
            null
        } else {
            video.description
        },
        thumbnail = AdaptiveImageProperty.Remote(
            url = video.thumbnailUrl,
            contentDescription = null,
            fallbackImageRes = drawableResources.icFallback
        ),
        creationTime = dateFormatter.formatWhenDate(
            startTimeMs = video.createdTimeMs,
            currentTimeMs = Calendar.getInstance().time.time
        ),
        onClick = onVideoClick
    )

}
