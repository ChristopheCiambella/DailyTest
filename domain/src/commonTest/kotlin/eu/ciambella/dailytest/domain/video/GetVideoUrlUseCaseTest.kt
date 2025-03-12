package eu.ciambella.dailytest.domain.video

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetVideoUrlUseCaseTest {

    @MockK
    lateinit var videoRepository: VideoRepository

    private lateinit var cut: GetVideoUrlUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = GetVideoUrlUseCase(videoRepository)
    }

    @Test
    fun `test video first page use case repository redirection`() = runTest {
        // Given
        coEvery { videoRepository.getVideoUrl(any()) } returns "VIDEO-URL"

        // When
        val result = cut.invoke("VIDEO-ID")

        // Then
        assertEquals("VIDEO-URL", result.getOrNull())
        coEvery { videoRepository.getVideoUrl("VIDEO-ID") }
    }

}
