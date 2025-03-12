package eu.ciambella.dailytest.common.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val HOME_ROUTE = "home"

fun NavController.navigateToHome() {
    navigate(HOME_ROUTE)
}

fun NavGraphBuilder.homeScreen() {
    composable(
        route = HOME_ROUTE
    ) {
        HomeRoute()
    }
}
