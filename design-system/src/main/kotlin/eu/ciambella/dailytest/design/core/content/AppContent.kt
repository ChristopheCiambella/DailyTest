package eu.ciambella.dailytest.design.core.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.ciambella.dailytest.design.property.content.ContentProperty
import eu.ciambella.dailytest.design.property.content.ErrorContentProperty
import eu.ciambella.dailytest.design.property.content.LazyColumnContentProperty
import eu.ciambella.dailytest.design.property.content.VideoPlayerContentProperty

@Composable
fun AppContent(
    property: ContentProperty,
    modifier: Modifier = Modifier,
) {
    when (property) {
        is ErrorContentProperty -> ErrorContent(
            property = property.property,
            modifier = modifier,
        )
        is LazyColumnContentProperty -> LazyColumnContent(
            property = property,
            modifier = modifier,
        )
        is VideoPlayerContentProperty -> VideoPlayerContent(
            property = property,
            modifier = modifier,
        )
    }
}
