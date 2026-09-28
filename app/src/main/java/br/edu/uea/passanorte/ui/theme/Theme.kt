package br.edu.uea.passanorte.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = Mint,
    onSecondary = GreenDeep,
    tertiary = CompassBrown,
    background = AppBackground,
    onBackground = NavyText,
    surface = AppBackground,
    onSurface = NavyText,
    surfaceVariant = FieldBlue,
    onSurfaceVariant = BodyText,
    outline = DividerBlue
)

private val DarkColorScheme = darkColorScheme(
    primary = Mint,
    secondary = GreenPrimary
)

@Composable
fun PassaNorteTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        content = content
    )
}