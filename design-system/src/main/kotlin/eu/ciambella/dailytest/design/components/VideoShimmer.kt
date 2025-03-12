package eu.ciambella.dailytest.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.ciambella.dailytest.design.R
import eu.ciambella.dailytest.design.atoms.shimmer.Shimmer
import eu.ciambella.dailytest.design.atoms.shimmer.ShimmerBrush

@Composable
fun VideoShimmer(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(R.dimen.one_unit_and_half),
                horizontal = dimensionResource(R.dimen.one_unit),
            )
    ) {
        Row {
            Shimmer(
                modifier = Modifier
                    .width(dimensionResource(R.dimen.eleven_units))
                    .height(dimensionResource(R.dimen.eight_units))
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.half_unit)))
                    .background(ShimmerBrush()),
            )
            Column(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(R.dimen.one_unit)
                    )
            ) {
                Shimmer(
                    modifier = Modifier
                        .height(24.dp)
                        .fillMaxWidth()
                )
                Shimmer(
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(R.dimen.half_unit)
                        )
                        .height(24.dp)
                        .width(175.dp)
                )
            }
        }
        Shimmer(
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.one_unit)
                )
                .height(20.dp)
                .fillMaxWidth()
        )
        Shimmer(
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.half_unit)
                )
                .height(20.dp)
                .fillMaxWidth()
        )
        Shimmer(
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.half_unit)
                )
                .height(20.dp)
                .fillMaxWidth()
        )
        Shimmer(
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.half_unit)
                )
                .height(20.dp)
                .fillMaxWidth()
        )
        Shimmer(
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.one_unit)
                )
                .height(20.dp)
                .width(200.dp)
        )
    }
}

@Preview
@Composable
fun VideoShimmerPreview() {
    VideoShimmer()
}
