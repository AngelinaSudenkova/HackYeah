package gdg.pjatk.pw.demo.theme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

val DarkColorTheme = darkColorScheme(
    primary = Primary,
    onPrimary = InputFieldTextColor,
    surface = White70PercentOpacity,
    onSurface = InputFieldTextColor,
    secondary = White50PercentOpacity,
    onSecondary = FilterButtonLabelColor,
    onSurfaceVariant = InputFieldLabelColor
)