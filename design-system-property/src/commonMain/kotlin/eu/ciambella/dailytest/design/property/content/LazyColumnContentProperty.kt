package eu.ciambella.dailytest.design.property.content

import eu.ciambella.dailytest.design.property.Property

data class LazyColumnContentProperty(
    val items: List<Property>,
    val scrollEnabled: Boolean = true,
) : ContentProperty
