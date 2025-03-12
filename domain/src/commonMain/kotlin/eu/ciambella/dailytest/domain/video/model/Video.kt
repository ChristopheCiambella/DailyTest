package eu.ciambella.dailytest.domain.video.model

data class Video(
    val id: String,
    val title: String,
    val description: String?,
    val thumbnailUrl: String?,
    val createdTimeMs: Long?,
)
