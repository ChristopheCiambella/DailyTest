package eu.ciambella.dailytest.domain.video

class GetVideoUrlUseCase(
    private val videoRepository: VideoRepository,
) {

    suspend fun invoke(
        videoId: String,
    ): Result<String> = runCatching {
        videoRepository.getVideoUrl(videoId)
    }

}
