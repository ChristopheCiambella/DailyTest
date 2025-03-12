package eu.ciambella.dailytest.data.repository.video.datasource

class HardcodedVideoUrlDatasource {

    companion object {
        private val VIDEO_URLS = listOf(
            "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8"
        )
    }

    fun getVideoUrl() = VIDEO_URLS.random()

}
