package eu.ciambella.dailytest.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun DailymotionTheme(
    content: @Composable () -> Unit
) {
    val colors = darkColorScheme(
        primary = Color(0xFF007EFF),
        secondary = Color(0xFF00A2FF),
        background = Color(0xFF121212),
        surface = Color(0xFF1E1E1E),
        onPrimary = Color.White,
        onSecondary = Color.White,
        onBackground = Color.White,
        onSurface = Color(0xFFB0B0B0)
    )
    val customColors = colors.copy(
        surfaceVariant = Color(0xFF252525)
    )
    MaterialTheme(
        colorScheme = customColors,
        content = content
    )
}
