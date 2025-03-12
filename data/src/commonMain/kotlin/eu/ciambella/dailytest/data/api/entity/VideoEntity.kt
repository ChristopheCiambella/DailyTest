package eu.ciambella.dailytest.data.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoEntity(
    @SerialName("id")
    val id: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("created_time")
    val createdTime: Int?,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String?,
)
