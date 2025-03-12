package eu.ciambella.dailytest.data.repository.video.datastore

import eu.ciambella.dailytest.data.api.response.VideosResponse
import eu.ciambella.dailytest.data.repository.video.datasource.FetchVideoApiDatasource
import eu.ciambella.dailytest.data.repository.video.datasource.HardcodedVideoUrlDatasource
import eu.ciambella.dailytest.data.repository.video.mapper.VideosResponseMapper
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

class FetchVideoDatastoreTest {

    @MockK
    private lateinit var fetchVideoApiDatasource: FetchVideoApiDatasource
    @MockK
    private lateinit var hardcodedVideoUrlDatasource: HardcodedVideoUrlDatasource
    @MockK
    private lateinit var videosResponseMapper: VideosResponseMapper

    private lateinit var cut: VideoDatastore

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = VideoDatastore(
            fetchVideoApiDatasource,
            hardcodedVideoUrlDatasource,
            videosResponseMapper
        )
    }

    @Test
    fun `test fetchVideos datasource redirection`() = runTest {
        // Given
        val expected = mockk<Videos>()
        val response = mockk<VideosResponse>()
        coEvery { fetchVideoApiDatasource.fetchVideos(any()) } returns response
        coEvery { videosResponseMapper.mapToVideos(response, any()) } returns expected

        // When
        val result = cut.fetchVideos(4)

        // Then
        assertEquals(expected, result)
        coVerify { fetchVideoApiDatasource.fetchVideos(4) }
        coVerify { videosResponseMapper.mapToVideos(response, 4) }
    }

    @Test
    fun `test fetchVideoUrl datasource redirection`() = runTest {
        // Given
        coEvery { hardcodedVideoUrlDatasource.getVideoUrl() } returns "VIDEO-URL"

        // When
        val result = cut.fetchVideoUrl("VIDEO-ID")

        // Then
        assertEquals("VIDEO-URL", result)
        coVerify { hardcodedVideoUrlDatasource.getVideoUrl() }
    }

}
