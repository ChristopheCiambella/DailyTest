package eu.ciambella.dailytest.design.property.scaffold

import eu.ciambella.dailytest.design.property.topbar.TopAppBarProperty
import eu.ciambella.dailytest.design.property.content.ContentProperty

data class ScaffoldProperty(
    val topAppBarProperty: TopAppBarProperty?,
    val contentProperty: ContentProperty,
)
