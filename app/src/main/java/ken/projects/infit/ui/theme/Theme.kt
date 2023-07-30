package ken.projects.infit.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = darkBlue,
    primaryVariant = lightBlue,
    secondary = holoGreen
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = holoGreen,
    primaryVariant = darkBlue,
    secondary = holoGreen,
    surface = lightBlue,
    onPrimary = white,
    onSurface = blueWhite,
    onSecondary = Color.White,
    onBackground = white,
    background = darkBlue
)

@Composable
fun InFitTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}