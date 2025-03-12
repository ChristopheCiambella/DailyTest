package eu.ciambella.dailytest.design.core.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.ciambella.dailytest.design.core.content.AppContent
import eu.ciambella.dailytest.design.core.topbar.AppTopAppBar
import eu.ciambella.dailytest.design.property.scaffold.ScaffoldProperty
import eu.ciambella.dailytest.design.theme.DailymotionTheme

@Composable
fun AppScaffold(
    property: ScaffoldProperty,
    modifier: Modifier = Modifier,
) {
    DailymotionTheme {
        Scaffold(
            modifier = modifier,
            topBar = {
                property.topAppBarProperty?.also {
                    AppTopAppBar(it)
                }
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    AppContent(
                        property = property.contentProperty,
                    )
                }
            }
        )
    }
}
