package eu.ciambella.dailytest.design.property.topbar

sealed class TopAppBarProperty {
    data class Default(
        val title: String
    ) : TopAppBarProperty()
}
