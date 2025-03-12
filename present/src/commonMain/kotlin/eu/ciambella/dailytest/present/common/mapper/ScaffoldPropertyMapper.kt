package eu.ciambella.dailytest.present.common.mapper

import eu.ciambella.dailytest.design.property.scaffold.ScaffoldProperty
import eu.ciambella.dailytest.design.property.topbar.TopAppBarProperty
import eu.ciambella.dailytest.design.property.content.ContentProperty

class ScaffoldPropertyMapper {

    fun map(
        topAppBarProperty: TopAppBarProperty? = null,
        contentProperty: ContentProperty,
    ) = ScaffoldProperty(
        topAppBarProperty = topAppBarProperty,
        contentProperty = contentProperty,
    )

}
