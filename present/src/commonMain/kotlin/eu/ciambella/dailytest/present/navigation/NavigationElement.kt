package eu.ciambella.dailytest.present.navigation

sealed class NavigationElement {

    data object Up : NavigationElement()

    data object Home : NavigationElement()

    data class Player(
        val videoId: String
    ) : NavigationElement()

}
