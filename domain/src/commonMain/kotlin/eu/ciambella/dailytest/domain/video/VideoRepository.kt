package eu.ciambella.dailytest.domain.video

import eu.ciambella.dailytest.domain.video.model.Videos

interface VideoRepository {
    suspend fun getVideos(page: Int): Videos
    suspend fun getVideoUrl(videoId: String): String
}
