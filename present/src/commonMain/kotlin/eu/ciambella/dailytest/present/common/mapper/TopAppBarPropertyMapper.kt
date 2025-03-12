package eu.ciambella.dailytest.present.common.mapper

import eu.ciambella.dailytest.design.property.topbar.TopAppBarProperty

class TopAppBarPropertyMapper {

    fun default(
        title: String
    ): TopAppBarProperty = TopAppBarProperty.Default(
        title = title,
    )

}
