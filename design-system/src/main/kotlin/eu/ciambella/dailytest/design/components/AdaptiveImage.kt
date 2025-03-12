package eu.ciambella.dailytest.design.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import eu.ciambella.dailytest.design.property.components.AdaptiveImageProperty

@Composable
fun AdaptiveImage(
    property: AdaptiveImageProperty,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
    colorFilter: ColorFilter? = null
) {
    when (property) {
        is AdaptiveImageProperty.Remote -> ImageRemote(
            property = property,
            modifier = modifier,
            contentScale = contentScale,
            alignment = alignment,
            colorFilter = colorFilter
        )

        is AdaptiveImageProperty.Resource -> Image(
            painter = painterResource(property.imageRes),
            contentDescription = property.contentDescription,
            modifier = modifier,
            contentScale = contentScale,
            alignment = alignment,
            colorFilter = colorFilter
        )
    }
}

@Composable
private fun ImageRemote(
    property: AdaptiveImageProperty.Remote,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
    colorFilter: ColorFilter? = null
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(property.url)
            .memoryCacheKey(property.url)
            .crossfade(true)
            .build(),
        contentDescription = property.contentDescription,
        error = painterResource(property.fallbackImageRes),
        modifier = modifier,
        alignment = alignment,
        contentScale = contentScale,
        colorFilter = colorFilter
    )
}
