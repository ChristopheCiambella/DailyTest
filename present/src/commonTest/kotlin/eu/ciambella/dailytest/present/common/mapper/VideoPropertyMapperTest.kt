package eu.ciambella.dailytest.present.common.mapper

import eu.ciambella.dailytest.design.property.components.AdaptiveImageProperty
import eu.ciambella.dailytest.domain.video.model.Video
import eu.ciambella.dailytest.present.common.formatter.DateFormatter
import eu.ciambella.dailytest.present.resource.DrawableResources
import eu.ciambella.dailytest.present.common.mapper.VideoPropertyMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class VideoPropertyMapperTest {

    private val drawableResources = mockk<DrawableResources> {
        every { icFallback } returns 0x123
    }
    private val dateFormatter = mockk<DateFormatter> {
        every { formatWhenDate(any(), any()) } returns "1 Jan 2024"
    }

    private val cut = VideoPropertyMapper(drawableResources, dateFormatter)

    @Test
    fun `mapToVideoProperty should correctly map a Video to VideoProperty`() {
        // Given
        val video = Video(
            id = "123",
            title = "Sample Video",
            description = "A sample description",
            thumbnailUrl = "http://sample.com/thumb.jpg",
            createdTimeMs = 1704067200_000L
        )

        // When
        val result = cut.mapToVideoProperty(video) {}

        // Then
        assertEquals("Sample Video", result.title)
        assertEquals("A sample description", result.description)
        assertEquals("http://sample.com/thumb.jpg", (result.thumbnail as AdaptiveImageProperty.Remote).url)
        assertEquals(0x123, (result.thumbnail as AdaptiveImageProperty.Remote).fallbackImageRes)
        assertEquals("1 Jan 2024", result.creationTime)
    }

    @Test
    fun `mapToVideoProperty should set description to null when empty`() {
        // Given
        val video = Video(
            id = "123",
            title = "Sample Video",
            description = "",
            thumbnailUrl = "http://sample.com/thumb.jpg",
            createdTimeMs = 1704067200_000L
        )

        // When
        val result = cut.mapToVideoProperty(video) {}

        // Then
        assertNull(result.description)
    }
}
