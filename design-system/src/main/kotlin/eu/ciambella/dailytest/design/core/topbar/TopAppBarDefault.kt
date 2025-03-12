package eu.ciambella.dailytest.design.core.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import eu.ciambella.dailytest.design.property.topbar.TopAppBarProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarDefault(
    property: TopAppBarProperty.Default,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = property.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    )
}

@Preview
@Composable
fun TopAppBarDefaultPreview() {
    TopAppBarDefault(
        TopAppBarProperty.Default("Title")
    )
}
