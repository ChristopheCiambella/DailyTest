package eu.ciambella.dailytest.common.screen.player

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val PLAYER_ROUTE = "player"

private const val VIDEO_ID_ARG = "playerVideoId"

internal class PlayerArgs(
    val videoId: String,
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        videoId = checkNotNull(savedStateHandle[VIDEO_ID_ARG])
    )
}

fun NavController.navigateToPlayer(
    videoId: String,
) {
    navigate("$PLAYER_ROUTE/$videoId")
}

fun NavGraphBuilder.playerScreen() {
    composable(
        route = "$PLAYER_ROUTE/{$VIDEO_ID_ARG}",
        arguments = listOf(
            navArgument(VIDEO_ID_ARG) {
                type = NavType.StringType
            },
        )
    ) {
        PlayerRoute()
    }
}
