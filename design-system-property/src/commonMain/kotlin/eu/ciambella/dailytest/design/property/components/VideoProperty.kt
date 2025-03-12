package eu.ciambella.dailytest.design.property.components

import eu.ciambella.dailytest.design.property.Property

data class VideoProperty(
    val title: String,
    val description: String?,
    val thumbnail: AdaptiveImageProperty,
    val creationTime: String?,
    val onClick: () -> Unit,
) : Property
