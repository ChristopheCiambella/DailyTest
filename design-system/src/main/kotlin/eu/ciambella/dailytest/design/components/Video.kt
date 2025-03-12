package eu.ciambella.dailytest.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import eu.ciambella.dailytest.design.R
import eu.ciambella.dailytest.design.atoms.shimmer.ShimmerBrush
import eu.ciambella.dailytest.design.property.components.AdaptiveImageProperty
import eu.ciambella.dailytest.design.property.components.VideoProperty

@Composable
fun Video(
    property: VideoProperty,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = property.onClick
            )
            .padding(
                vertical = dimensionResource(R.dimen.one_unit_and_half),
                horizontal = dimensionResource(R.dimen.one_unit),
            )
    ) {
        Row {
            AdaptiveImage(
                property = property.thumbnail,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(dimensionResource(R.dimen.eleven_units))
                    .height(dimensionResource(R.dimen.eight_units))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.half_unit)))
                    .background(ShimmerBrush()),
            )
            Text(
                text = property.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.one_unit)
                )
            )
        }
        property.description?.also { description ->
            val spanned = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY)
            Text(
                text = buildAnnotatedString {
                    append(spanned.toString())
                },
                fontSize = 18.sp,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.one_unit)
                )
            )
        }
        property.creationTime?.also { creationTime ->
            Text(
                text = creationTime,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.one_unit)
                )
            )
        }
    }
}

@Preview
@Composable
fun VideoFullPreview() {
    Video(
        VideoProperty(
            title = "Video title",
            description = "Video description",
            thumbnail = AdaptiveImageProperty.Resource(
                imageRes = R.drawable.ic_android,
                contentDescription = null
            ),
            creationTime = "Il y a 16 minutes",
            onClick = {},
        )
    )
}

@Preview
@Composable
fun VideoMinimalistPreview() {
    Video(
        VideoProperty(
            title = "Video title",
            description = null,
            thumbnail = AdaptiveImageProperty.Resource(
                imageRes = R.drawable.ic_android,
                contentDescription = null
            ),
            creationTime = null,
            onClick = {},
        )
    )
}
