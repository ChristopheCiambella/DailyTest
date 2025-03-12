package eu.ciambella.dailytest.data.repository.video

import eu.ciambella.dailytest.data.repository.video.datastore.VideoDatastore
import eu.ciambella.dailytest.domain.video.VideoRepository
import eu.ciambella.dailytest.domain.video.model.Videos

class VideoRepositoryImpl(
    private val videoDatastore: VideoDatastore
) : VideoRepository {

    override suspend fun getVideos(
        page: Int
    ): Videos = videoDatastore.fetchVideos(page)

    override suspend fun getVideoUrl(
        videoId: String
    ): String = videoDatastore.fetchVideoUrl(videoId)

}
