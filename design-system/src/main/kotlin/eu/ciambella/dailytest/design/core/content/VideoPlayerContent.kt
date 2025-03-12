package eu.ciambella.dailytest.design.core.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.ciambella.dailytest.design.components.VideoPlayer
import eu.ciambella.dailytest.design.property.content.VideoPlayerContentProperty

@Composable
fun VideoPlayerContent(
    property: VideoPlayerContentProperty,
    modifier: Modifier = Modifier,
) {
    VideoPlayer(
        property = property.videoPlayerProperty,
        modifier = modifier.fillMaxSize()
    )
}
