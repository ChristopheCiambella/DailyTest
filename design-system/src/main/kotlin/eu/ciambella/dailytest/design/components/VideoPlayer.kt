package eu.ciambella.dailytest.design.components

import android.app.Activity
import android.view.View
import android.view.WindowManager
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import eu.ciambella.dailytest.design.property.components.VideoPlayerProperty
import eu.ciambella.dailytest.design.utils.SystemUIUtils

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(
    property: VideoPlayerProperty,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(property.videoUrl))
            prepare()
            playWhenReady = true
        }
    }
    val activity = context as? Activity
    LaunchedEffect(Unit) {
        SystemUIUtils.hideSystemUI(activity?.window)
    }
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
            SystemUIUtils.showSystemUI(activity?.window)
        }
    }
    Box(modifier) {
        AndroidView(
            factory = {
                PlayerView(it).apply {
                    player = exoPlayer
                    useController = true
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
