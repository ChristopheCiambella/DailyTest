package eu.ciambella.dailytest.present.navigation

sealed interface Action {

    data class Navigation(
        val navigationElement: NavigationElement,
    ) : Action

    data object None : Action

}
