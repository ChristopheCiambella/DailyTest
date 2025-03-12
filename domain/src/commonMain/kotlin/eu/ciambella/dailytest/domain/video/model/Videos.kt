package eu.ciambella.dailytest.domain.video.model

data class Videos(
    val items: List<Video>,
    val nextPage: Int,
    val hasMore: Boolean
)
