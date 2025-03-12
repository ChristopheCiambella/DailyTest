package eu.ciambella.dailytest.domain.video

import eu.ciambella.dailytest.domain.video.GetVideosUseCase
import eu.ciambella.dailytest.domain.video.VideoRepository
import eu.ciambella.dailytest.domain.video.model.Videos
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetVideosUseCaseTest {

    @MockK
    lateinit var videoRepository: VideoRepository

    private lateinit var cut: GetVideosUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = GetVideosUseCase(videoRepository)
    }

    @Test
    fun `test video first page use case repository redirection`() = runTest {
        // Given
        val expected = mockk<Videos>()
        coEvery { videoRepository.getVideos(any()) } returns expected

        // When
        val result = cut.invoke()

        // Then
        assertEquals(expected, result.getOrNull())
        coEvery { videoRepository.getVideos(1) }
    }

    @Test
    fun `test video next page use case repository redirection`() = runTest {
        // Given
        val expected = mockk<Videos>()
        coEvery { videoRepository.getVideos(any()) } returns expected

        // When
        val result = cut.invoke(2)

        // Then
        assertEquals(expected, result.getOrNull())
        coEvery { videoRepository.getVideos(2) }
    }

}
