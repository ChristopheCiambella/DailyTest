package eu.ciambella.dailytest.data.api.response

import eu.ciambella.dailytest.data.api.entity.VideoEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideosResponse(
    @SerialName("page")
    val page: Int?,
    @SerialName("limit")
    val limit: Int?,
    @SerialName("explicit")
    val explicit: Boolean?,
    @SerialName("total")
    val total: Int?,
    @SerialName("has_more")
    val hasMore: Boolean?,
    @SerialName("list")
    val list: List<VideoEntity>?,
)
