package eu.ciambella.dailytest.design.property.components

import eu.ciambella.dailytest.design.property.Property

data class ErrorProperty(
    val icon: AdaptiveImageProperty?,
    val title: String?,
    val message: String?,
    val action: String?,
    val onActionClick: (() -> Unit)?
) : Property
