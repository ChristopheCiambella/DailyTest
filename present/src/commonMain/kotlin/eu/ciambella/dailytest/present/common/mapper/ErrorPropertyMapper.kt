package eu.ciambella.dailytest.present.common.mapper

import eu.ciambella.dailytest.design.property.components.AdaptiveImageProperty
import eu.ciambella.dailytest.design.property.components.ErrorProperty
import eu.ciambella.dailytest.present.resource.DrawableResources
import eu.ciambella.dailytest.present.resource.StringResources

class ErrorPropertyMapper(
    private val drawableResources: DrawableResources,
    private val stringResources: StringResources,
) {

    fun genericError(
        onClick: (() -> Unit)? = null
    ) = ErrorProperty(
        icon = AdaptiveImageProperty.Resource(
            imageRes = drawableResources.icError,
            contentDescription = null,
        ),
        title = stringResources.errorUnavailableTitle,
        message = stringResources.errorUnavailableMessage,
        action = if (onClick != null) {
            stringResources.errorActionRetry
        } else {
            null
        },
        onActionClick = onClick
    )

}
