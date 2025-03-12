package eu.ciambella.dailytest.design.property.components

sealed class AdaptiveImageProperty(
    open val contentDescription: String?,
) {
    data class Remote(
        override val contentDescription: String?,
        val url: String?,
        val fallbackImageRes: Int,
    ) : AdaptiveImageProperty(contentDescription)

    data class Resource(
        val imageRes: Int,
        override val contentDescription: String?,
    ) : AdaptiveImageProperty(contentDescription)

}
