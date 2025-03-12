package eu.ciambella.dailytest.data.repository.video.datasource

import org.junit.Assert.assertEquals
import org.junit.Test

class HardcodedVideoUrlDatasourceTest {

    private val cut = HardcodedVideoUrlDatasource()

    @Test
    fun `test getVideoUrl`() {
        // When
        val result = cut.getVideoUrl()

        // Then
        assertEquals("https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8", result)
    }

}
