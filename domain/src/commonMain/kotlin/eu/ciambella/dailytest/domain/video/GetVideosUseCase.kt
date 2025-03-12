package eu.ciambella.dailytest.domain.video

import eu.ciambella.dailytest.domain.video.model.Videos

class GetVideosUseCase(
    private val videoRepository: VideoRepository,
) {

    companion object {
        private const val FIRST_PAGE = 1
    }

    suspend fun invoke(
        page: Int = FIRST_PAGE
    ): Result<Videos> = runCatching {
        videoRepository.getVideos(page)
    }

}
