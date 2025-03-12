package eu.ciambella.dailytest.data.repository.video.mapper

import eu.ciambella.dailytest.data.api.entity.VideoEntity
import eu.ciambella.dailytest.data.api.response.VideosResponse
import eu.ciambella.dailytest.data.repository.video.mapper.VideosResponseMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class VideoResponseMapperTest {

    private lateinit var cut: VideosResponseMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = VideosResponseMapper()
    }

    @Test
    fun `test mapToVideos with response null must be return minimalist result`() {
        // Given
        val response = mockk<VideosResponse> {
            coEvery { list } returns null
            coEvery { page } returns null
            coEvery { hasMore } returns null
        }

        // When
        val result = cut.mapToVideos(response, 4)

        // Then
        assertEquals(0, result.items.size)
        assertEquals(5, result.nextPage)
        assertFalse(result.hasMore)
    }

    @Test
    fun `test mapToVideos with correct response must be return valid result`() {
        // Given
        val response = mockk<VideosResponse> {
            coEvery { list } returns listOf(
                mockk(relaxed = true),
                mockk(relaxed = true),
                mockk(relaxed = true),
            )
            coEvery { page } returns 4
            coEvery { hasMore } returns true
        }

        // When
        val result = cut.mapToVideos(response, 4)

        // Then
        assertEquals(3, result.items.size)
        assertEquals(5, result.nextPage)
        assertTrue(result.hasMore)
    }

    @Test
    fun `test mapVideoEntity with id null must be return null`() = runTest {
        // Given
        val entity = mockk<VideoEntity> {
            coEvery { id } returns null
        }

        // When
        val result = cut.mapVideoEntity(entity)

        // Then
        assertNull(result)
    }

    @Test
    fun `test mapVideoEntity with title null must be return null`() = runTest {
        // Given
        val entity = mockk<VideoEntity> {
            coEvery { id } returns "42"
            coEvery { title } returns null
        }

        // When
        val result = cut.mapVideoEntity(entity)

        // Then
        assertNull(result)
    }

    @Test
    fun `test mapVideoEntity with data must be return valid result`() = runTest {
        // Given
        val entity = mockk<VideoEntity> {
            coEvery { id } returns "42"
            coEvery { title } returns "title"
            coEvery { description } returns "description"
            coEvery { thumbnailUrl } returns "thumbnailUrl"
            coEvery { createdTime } returns 123456789
        }

        // When
        val result = cut.mapVideoEntity( entity)

        // Then
        requireNotNull(result)
        assertEquals("42", result.id)
        assertEquals("title", result.title)
        assertEquals("description", result.description)
        assertEquals("thumbnailUrl", result.thumbnailUrl)
        assertEquals(123456789_000L, result.createdTimeMs)
    }

    @Test
    fun `test mapVideoEntity with minimalist data must be return valid result`() = runTest {
        // Given
        val entity = mockk<VideoEntity> {
            coEvery { id } returns "42"
            coEvery { title } returns "title"
            coEvery { description } returns null
            coEvery { thumbnailUrl } returns null
            coEvery { createdTime } returns null
        }

        // When
        val result = cut.mapVideoEntity(entity)

        // Then
        requireNotNull(result)
        assertEquals("42", result.id)
        assertEquals("title", result.title)
        assertNull(result.description)
        assertNull(result.thumbnailUrl)
        assertNull(result.createdTimeMs)
    }

}
