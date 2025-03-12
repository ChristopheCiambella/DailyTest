package eu.ciambella.dailytest.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import eu.ciambella.dailytest.design.R
import eu.ciambella.dailytest.design.property.components.AdaptiveImageProperty
import eu.ciambella.dailytest.design.property.components.ErrorProperty

@Composable
fun ErrorFeed(
    property: ErrorProperty,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.three_units),
                vertical = dimensionResource(R.dimen.three_units)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        property.icon?.also { icon ->
            AdaptiveImage(
                property = icon,
                modifier = Modifier
                    .width(dimensionResource(R.dimen.four_units))
                    .height(dimensionResource(R.dimen.four_units)),
            )
            Spacer(Modifier.height(dimensionResource(R.dimen.two_units)))
        }
        property.title?.also { title ->
            Text(
                text = title,
                textAlign = TextAlign.Center
            )
        }
        property.message?.also { message ->
            Text(
                text = message,
                textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.height(dimensionResource(R.dimen.two_units)))
        property.action?.also { action ->
            Button(
                onClick = {
                    property.onActionClick?.invoke()
                }
            ) {
                Text(action)
            }
        }
    }
}

@Preview
@Composable
fun ErrorFeedPreview() {
    ErrorFeed(
        property = ErrorProperty(
            icon = AdaptiveImageProperty.Resource(
                imageRes = R.drawable.ic_error,
                contentDescription = null
            ),
            title = "Impossible de charger cette page",
            message = "Vérifiez votre connexion internet.",
            action = "Réessayer",
            onActionClick = null
        )
    )
}

