package eu.ciambella.dailytest.present.navigation

import eu.ciambella.dailytest.present.utils.SingleEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.update

class MainNavigator {

    private val navigationFlow = MutableStateFlow<SingleEvent<NavigationElement>?>(null)

    fun navigationEvents(): Flow<NavigationElement> = navigationFlow
        .filterNotNull()
        .mapNotNull {
            it.getContentIfNotHandled()
        }

    fun navigateToHome() {
        navigateTo(
            NavigationElement.Home
        )
    }

    fun navigateToUp() {
        navigateTo(NavigationElement.Up)
    }

    fun navigateTo(navigationElement: NavigationElement) {
        navigationFlow.update {
            SingleEvent(navigationElement)
        }
    }

}
