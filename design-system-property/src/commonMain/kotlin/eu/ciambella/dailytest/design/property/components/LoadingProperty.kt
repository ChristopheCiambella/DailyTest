package eu.ciambella.dailytest.design.property.components

import eu.ciambella.dailytest.design.property.Property

data class LoadingProperty(
    val nextCallback: () -> Unit
) : Property
