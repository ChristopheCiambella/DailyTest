package eu.ciambella.dailytest.design.core.content

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.ciambella.dailytest.design.atoms.Divider
import eu.ciambella.dailytest.design.components.ErrorFeed
import eu.ciambella.dailytest.design.components.Loading
import eu.ciambella.dailytest.design.components.Video
import eu.ciambella.dailytest.design.components.VideoPlayer
import eu.ciambella.dailytest.design.components.VideoShimmer
import eu.ciambella.dailytest.design.property.components.DividerProperty
import eu.ciambella.dailytest.design.property.components.ErrorProperty
import eu.ciambella.dailytest.design.property.components.LoadingProperty
import eu.ciambella.dailytest.design.property.components.VideoPlayerProperty
import eu.ciambella.dailytest.design.property.components.VideoProperty
import eu.ciambella.dailytest.design.property.components.VideoShimmerProperty
import eu.ciambella.dailytest.design.property.content.LazyColumnContentProperty

@Composable
fun LazyColumnContent(
    property: LazyColumnContentProperty,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        userScrollEnabled = property.scrollEnabled,
        modifier = modifier,
    ) {
        items(items = property.items) { item ->
            when (item) {
                is DividerProperty -> Divider()
                is VideoProperty -> Video(item)
                is VideoShimmerProperty -> VideoShimmer()
                is VideoPlayerProperty -> VideoPlayer(item)
                is LoadingProperty -> Loading(item)
                is ErrorProperty -> ErrorFeed(item)
            }
        }
    }
}
