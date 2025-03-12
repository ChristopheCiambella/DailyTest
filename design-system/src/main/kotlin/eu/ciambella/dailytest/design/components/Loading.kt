package eu.ciambella.dailytest.design.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import eu.ciambella.dailytest.design.R
import eu.ciambella.dailytest.design.property.components.LoadingProperty

@Composable
fun Loading(
    property: LoadingProperty? = null,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.two_units))
                .size(dimensionResource(R.dimen.five_units)),
            color = colorResource(R.color.loading),
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
    property?.nextCallback?.invoke()
}

@Preview
@Composable
fun LoadingPreview() {
    Loading()
}
