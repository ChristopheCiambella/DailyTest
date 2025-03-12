package eu.ciambella.dailytest.data.repository.video.datastore

import eu.ciambella.dailytest.data.repository.video.datasource.FetchVideoApiDatasource
import eu.ciambella.dailytest.data.repository.video.datasource.HardcodedVideoUrlDatasource
import eu.ciambella.dailytest.data.repository.video.mapper.VideosResponseMapper
import eu.ciambella.dailytest.domain.video.model.Videos

class VideoDatastore(
    private val fetchVideoApiDatasource: FetchVideoApiDatasource,
    private val hardcodedVideoUrlDatasource: HardcodedVideoUrlDatasource,
    private val videosResponseMapper: VideosResponseMapper
) {

    suspend fun fetchVideos(
        page: Int
    ): Videos {
        val videosResponse = fetchVideoApiDatasource.fetchVideos(page)
        return videosResponseMapper.mapToVideos(
            response = videosResponse,
            requestedPage = page
        )
    }

    fun fetchVideoUrl(
        videoId: String
    ): String = hardcodedVideoUrlDatasource.getVideoUrl()

}
