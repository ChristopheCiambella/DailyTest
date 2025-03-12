package eu.ciambella.dailytest.design.core.topbar

import androidx.compose.runtime.Composable
import eu.ciambella.dailytest.design.property.topbar.TopAppBarProperty

@Composable
fun AppTopAppBar(
    property: TopAppBarProperty,
) {
    when (property) {
        is TopAppBarProperty.Default -> TopAppBarDefault(property)
    }
}
