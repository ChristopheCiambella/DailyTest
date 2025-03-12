package eu.ciambella.dailytest.data.repository.video

import eu.ciambella.dailytest.data.repository.video.datastore.VideoDatastore
import eu.ciambella.dailytest.domain.video.model.Videos
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class VideoRepositoryImplTest {

    @MockK
    private lateinit var fetchVideoDatastore: VideoDatastore

    private lateinit var cut: VideoRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = VideoRepositoryImpl(fetchVideoDatastore)
    }

    @Test
    fun `test getVideos datastore redirection`() = runTest {
        // Given
        val expected = mockk<Videos>()
        coEvery { fetchVideoDatastore.fetchVideos(any()) } returns expected

        // When
        val result = cut.getVideos(4)

        // Then
        assertEquals(expected, result)
        coVerify { fetchVideoDatastore.fetchVideos(4) }
    }

    @Test
    fun `test getVideoUrl datastore redirection`() = runTest {
        // Given
        coEvery { fetchVideoDatastore.fetchVideoUrl(any()) } returns "VIDEO-URL"

        // When
        val result = cut.getVideoUrl("VIDEO-ID")

        // Then
        assertEquals("VIDEO-URL", result)
        coVerify { fetchVideoDatastore.fetchVideoUrl("VIDEO-ID") }
    }

}
