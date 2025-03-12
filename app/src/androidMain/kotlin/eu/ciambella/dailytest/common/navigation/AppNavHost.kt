package eu.ciambella.dailytest.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import eu.ciambella.dailytest.present.navigation.MainNavigator
import eu.ciambella.dailytest.common.screen.home.homeScreen
import eu.ciambella.dailytest.common.screen.player.playerScreen
import org.koin.compose.koinInject

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    val mainNavigator: MainNavigator = koinInject<MainNavigator>()
    val navigationConsumer: NavigationConsumer = koinInject<NavigationConsumer>()
    LaunchedEffect("navigation") {
        mainNavigator.navigationEvents().collect { navigationElement ->
            navigationConsumer.handle(
                navHostController = navHostController,
                navigationElement = navigationElement,
            )
        }
    }
    CompositionLocalProvider {
        NavHost(
            navController = navHostController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            homeScreen()
            playerScreen()
        }
    }
}
