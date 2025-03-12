package eu.ciambella.dailytest.common.navigation

import androidx.navigation.NavHostController
import eu.ciambella.dailytest.present.navigation.NavigationElement
import eu.ciambella.dailytest.common.screen.home.navigateToHome
import eu.ciambella.dailytest.common.screen.player.navigateToPlayer

class NavigationConsumer {

    fun handle(
        navHostController: NavHostController,
        navigationElement: NavigationElement,
    ) {
        when (navigationElement) {
            is NavigationElement.Up -> navHostController.popBackStack()
            is NavigationElement.Home -> navHostController.navigateToHome()
            is NavigationElement.Player -> navHostController.navigateToPlayer(
                videoId = navigationElement.videoId
            )
        }
    }

}
