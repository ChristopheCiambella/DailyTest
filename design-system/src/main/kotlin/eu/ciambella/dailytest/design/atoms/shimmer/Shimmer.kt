package eu.ciambella.dailytest.design.atoms.shimmer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import eu.ciambella.dailytest.design.R

@Composable
fun Shimmer(
    modifier: Modifier = Modifier,
    roundedCornerShapeSize: Dp = dimensionResource(R.dimen.half_unit)
) = Spacer(
    modifier
        .clip(RoundedCornerShape(roundedCornerShapeSize))
        .shimmerEffect()
)

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF)
fun ShimmerPreview() {
    Shimmer()
}
